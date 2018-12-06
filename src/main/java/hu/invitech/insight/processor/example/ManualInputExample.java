package hu.invitech.insight.processor.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.invitech.insight.processor.example.api.*;
import hu.invitech.insight.processor.example.data.Resource;
import hu.invitech.insight.processor.example.data.Server;
import hu.invitech.insight.processor.sdk.v1.function.FunctionCall;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo;
import hu.invitech.insight.processor.sdk.v1.server.ChangeToken;
import hu.invitech.insight.processor.sdk.v1.server.ServerInfo;
import hu.invitech.insight.processor.sdk.v1.server.Status;
import hu.invitech.insight.processor.sdk.v1.util.ApiConstants;
import hu.invitech.insight.processor.sdk.v1.util.ProcessorUtils;
import io.jsondb.JsonDBTemplate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static hu.invitech.insight.processor.sdk.core.util.CoreApiConstants.CORE_PATH;
import static hu.invitech.insight.processor.sdk.v1.util.ApiConstants.*;
import static spark.Spark.*;

public class ManualInputExample {
    private static final Logger log = Logger.getLogger(ManualInputExample.class.getName());

    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final JsonDBTemplate jsonDb = new JsonDBTemplate("./", "hu.invitech.insight.processor.example.data");

    public static final int dataVersion = 1;
    public static final int protocolVersion = 1;

    public static void main(String[] args) {
        ManualInputExample app = new ManualInputExample();

        if (!jsonDb.collectionExists(Server.class)) {
            jsonDb.createCollection(Server.class);
        }
        if (!jsonDb.collectionExists(Resource.class)) {
            jsonDb.createCollection(Resource.class);
        }

        port(80);

        staticFiles.location("/public");

        get("/swagger", (request, response) -> {
            response.redirect("/swagger/index.html");
            return null;
        });

        path(CORE_PATH, () -> {
            get("/version", "application/json", (request, response) -> {
                response.type("application/json");
                return new VersionApi().getVersion();
            });
        });

        path("/v1", () -> {
            before(FUNCTION_PATH, app::authorize);
            before(FUNCTION_PATH + "/*", app::authorize);
            before(RESOURCE_PATH, app::authorize);
            before(RESOURCE_PATH + "/*", app::authorize);

            path(FUNCTION_PATH, () -> {
                get("", "application/json", (request, response) -> {
                    response.type("application/json");
                    return new FunctionApi(request.session().attribute("server")).getFunctionMap();
                }, objectMapper::writeValueAsString);

                post(
                    "",
                    "application/json", (request, response) -> {
                        response.type("application/json");
                        return new FunctionApi(request.session().attribute("server")).callFunction(objectMapper.readValue(request.body() != null && !request.body().isEmpty() ?
                            request.body() :
                            "{}", FunctionCall.class));
                    },
                    objectMapper::writeValueAsString
                );
            });

            path(RESOURCE_PATH, () -> {
                get("", "application/json", (request, response) -> {
                    response.type("application/json");
                    return new ResourceApi(request.session().attribute("server")).getResourceTypeMap();
                }, objectMapper::writeValueAsString);

                post("", "application/json", (request, response) -> {
                    new ResourceApi(request.session().attribute("server")).addResource(objectMapper.readValue(request.body() != null && !request.body().isEmpty() ?
                        request.body() :
                        "{}", ResourceInfo.class));
                    response.type("application/json");
                    response.status(204);
                    return "";
                });

                delete("/:id", "application/json", (request, response) -> {
                    new ResourceApi(request.session().attribute("server")).removeResource(Integer.parseInt(request.params(":id")));
                    response.type("application/json");
                    response.status(204);
                    return "";
                });
            });

            path(SERVER_PATH, () -> {
                post(
                    ApiConstants.ASSIGN_SERVER_PATH,
                    "application/json", (request, response) -> {
                        response.type("application/json");
                        return new ServerApi().assignServer(objectMapper.readValue(
                            request.body() != null && !request.body().isEmpty() ? request.body() : "{}",
                            ServerInfo.class
                        ));
                    },
                    objectMapper::writeValueAsString
                );

                post(ApiConstants.UNASSIGN_SERVER_PATH, "application/json", (request, response) -> {
                    new ServerApi().unassignServer(objectMapper.readValue(request.body() != null && !request.body().isEmpty() ? request.body() : "{}", ServerInfo.class));
                    response.type("application/json");
                    response.status(204);
                    return "";
                });

                post(ApiConstants.CHANGE_TOKEN_PATH, "application/json", (request, response) -> {
                    new ServerApi().changeToken(objectMapper.readValue(request.body() != null && !request.body().isEmpty() ? request.body() : "{}", ChangeToken.class));
                    response.type("application/json");
                    response.status(204);
                    return "";
                });
            });
        });

        Runtime.getRuntime().addShutdownHook(new Thread(app::onStop));

        awaitInitialization();

        app.onStart();
    }

    public static okhttp3.Response addAuthorizationHeader(Interceptor.Chain chain, String token) throws IOException {
        okhttp3.Request request = chain.request().newBuilder().addHeader("Authorization", ApiConstants.AUTHENTICATION_SCHEME + " " + token).build();
        return chain.proceed(request);
    }

    private void authorize(final Request request, final Response response) {
        String authorizationHeader = request.headers("Authorization");
        if (authorizationHeader == null || !authorizationHeader.toLowerCase().startsWith(ApiConstants.AUTHENTICATION_SCHEME.toLowerCase() + " ")) {
            log.info("Unauthorized: no token found.");
            haltWithUnauthorized(response);
            return;
        }

        String token = authorizationHeader.substring(ApiConstants.AUTHENTICATION_SCHEME.length()).trim();
        try {
            Claims claims = ProcessorUtils.validateToken(token);

            String baseUrl = claims.getIssuer();
            Integer processorId = claims.get("processorId", Integer.class);

            String jxQuery = String.format("/.[token='%s']", token);
            List<Server> serverList = jsonDb.find(jxQuery, Server.class);
            if (serverList == null || serverList.isEmpty() || !serverList.get(0).getBaseUrl().equals(baseUrl) || !serverList.get(0).getProcessorId().equals(processorId)) {
                halt(403);
            }
            Server server = serverList.get(0);

            request.session(true).attribute("server", server);
        } catch (JwtException e) {
            log.info("Unauthorized: token invalid.");
            haltWithUnauthorized(response);
        }
    }

    private void haltWithUnauthorized(final Response response) {
        response.header("WWW-Authenticate", ApiConstants.AUTHENTICATION_SCHEME);
        halt(401);
    }

    private void onStart() {
        for (Server server : jsonDb.findAll(Server.class)) {
            try {
                final PlatformApi platform = getPlatformApi(server);
                retrofit2.Response<Void> response = platform.start(new Status(dataVersion, protocolVersion, new LinkedList<>())).execute();
                if (response.code() == 403) {
                    jsonDb.remove(server, Server.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onStop() {
        for (Server server : jsonDb.findAll(Server.class)) {
            try {
                final PlatformApi platform = getPlatformApi(server);
                platform.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PlatformApi getPlatformApi(final Server server) {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(server.getBaseUrl().endsWith("/") ? server.getBaseUrl() : server.getBaseUrl() + "/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(new OkHttpClient().newBuilder()
                .addInterceptor((chain) -> ManualInputExample.addAuthorizationHeader(chain, server.getToken()))
                .build())
            .build();

        return retrofit.create(PlatformApi.class);
    }
}
