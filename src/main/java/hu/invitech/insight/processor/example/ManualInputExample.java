package hu.invitech.insight.processor.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.invitech.insight.processor.sdk.core.api.VersionInterface;
import hu.invitech.insight.processor.sdk.v1.api.FunctionInterface;
import hu.invitech.insight.processor.sdk.v1.api.ResourceInterface;
import hu.invitech.insight.processor.sdk.v1.api.ServerInterface;
import hu.invitech.insight.processor.sdk.v1.data.JsonDataType;
import hu.invitech.insight.processor.sdk.v1.function.FunctionCall;
import hu.invitech.insight.processor.sdk.v1.function.FunctionMap;
import hu.invitech.insight.processor.sdk.v1.function.FunctionReturn;
import hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeMap;
import hu.invitech.insight.processor.sdk.v1.server.ChangeToken;
import hu.invitech.insight.processor.sdk.v1.server.ServerInfo;
import hu.invitech.insight.processor.sdk.v1.server.Status;
import hu.invitech.insight.processor.sdk.v1.util.ApiConstants;
import hu.invitech.insight.processor.sdk.v1.util.ProcessorUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.logging.Logger;

import static hu.invitech.insight.processor.sdk.core.util.CoreApiConstants.CORE_PATH;
import static hu.invitech.insight.processor.sdk.v1.resource.SensorTypes.*;
import static hu.invitech.insight.processor.sdk.v1.util.ApiConstants.*;
import static spark.Spark.*;

public class ManualInputExample implements VersionInterface, ResourceInterface, FunctionInterface, ServerInterface {
    private static final Logger log = Logger.getLogger(ManualInputExample.class.getName());

    private static final int dataVersion = 1;
    private static final int protocolVersion = 1;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, Server> serverMap = new LinkedHashMap<>();
    private final ResourceTypeMap resourceTypeMap = new ResourceTypeMap();

    public ManualInputExample() {
        ResourceTypeInfo resourceTypeInfo = new ResourceTypeInfo("MANUAL_INPUT");

        List<IncomingDataInfo> incomingDataInfoList = new LinkedList<>();
        incomingDataInfoList.add(new IncomingDataInfo("temp", JsonDataType.DOUBLE, TEMPERATURE, null, null));
        incomingDataInfoList.add(new IncomingDataInfo("humidity", JsonDataType.DOUBLE, HUMIDITY, null, null));
        incomingDataInfoList.add(new IncomingDataInfo("pressure", JsonDataType.DOUBLE, PRESSURE, null, null));
        incomingDataInfoList.add(new IncomingDataInfo("battery", JsonDataType.DOUBLE, BATTERY_PERCENTAGE, null, null));

        resourceTypeInfo.getIncomingData().put("MANUAL_DATA", incomingDataInfoList);

        resourceTypeMap.getResourceTypes().add(resourceTypeInfo);
    }

    public static void main(String[] args) {
        ManualInputExample app = new ManualInputExample();

        port(80);

        staticFiles.location("/public");

        get("/swagger", (request, response) -> {
            response.redirect("/swagger/index.html");
            return null;
        });

        path(CORE_PATH, () -> {
            get("/version", (request, response) -> protocolVersion);
        });

        path("/v1", () -> {
            path(FUNCTION_PATH, () -> {
                before(app::authorize);

                get("/", (request, response) -> app.getFunctionMap(), app::convertToJson);

                post("/", (request, response) -> app.callFunction(app.objectMapper.readValue(request.body(), FunctionCall.class)), app::convertToJson);
            });

            path(RESOURCE_PATH, () -> {
                before(app::authorize);

                get("/", (request, response) -> app.resourceTypeMap, app::convertToJson);

                post("/", (request, response) -> {
                    app.addResource(app.objectMapper.readValue(request.body(), ResourceInfo.class));
                    return null;
                });

                delete("/:id", (request, response) -> {
                    app.removeResource(Integer.parseInt(request.params(":id")));
                    return null;
                });
            });

            path(SERVER_PATH, () -> {
                post(ApiConstants.ASSIGN_SERVER_PATH, (request, response) -> app.assignServer(app.objectMapper.readValue(request.body(), ServerInfo.class)), app::convertToJson);

                post(ApiConstants.UNASSIGN_SERVER_PATH, (request, response) -> {
                    app.unassignServer(app.objectMapper.readValue(request.body(), ServerInfo.class));
                    return null;
                });

                post(ApiConstants.CHANGE_TOKEN_PATH, (request, response) -> {
                    app.changeToken(app.objectMapper.readValue(request.body(), ChangeToken.class));
                    return null;
                });
            });
        });

        Runtime.getRuntime().addShutdownHook(new Thread(app::onStop));

        awaitInitialization();

        app.onStart();
    }

    private void onStart() {
        for (Server server : serverMap.values()) {
            try {
                /*Response response = ClientBuilder.newClient()
                    .target(server.getBaseUrl())
                    .path("/v" + protocolVersion)
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, ApiConstants.AUTHENTICATION_SCHEME + " " + server.getToken())
                    .buildPost(Entity.json(new Status(
                        dataVersion,
                        protocolVersion,
                        new LinkedList<>()
                    )))
                    .invoke();
                if (response.getStatus() == Response.Status.FORBIDDEN.getStatusCode()) {
                    serverMap.remove(server.getToken());
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onStop() {
        for (Server server : serverMap.values()) {
            try {
                    /*ClientBuilder.newClient()
                        .target(server.getBaseUrl())
                        .path("/v" + protocolVersion)
                        .request(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, ApiConstants.AUTHENTICATION_SCHEME + " " + server.getToken())
                        .buildDelete()
                        .invoke();*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String convertToJson(Object model) throws JsonProcessingException {
        return objectMapper.writeValueAsString(model);
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

            Server server = serverMap.get(token);
            if (server == null || !server.getBaseUrl().equals(baseUrl) || !server.getProcessorId().equals(processorId)) {
                halt(403, "Forbidden: server parameter mismatch.");
            }

            request.session(true).attribute("server", server);
        } catch (JwtException e) {
            log.info("Unauthorized: token invalid.");
            haltWithUnauthorized(response);
        }
    }

    private void haltWithUnauthorized(final Response response) {
        response.header("WWW-Authenticate", ApiConstants.AUTHENTICATION_SCHEME);
        halt("401");
    }

    @Override
    public Integer getVersion() {
        return protocolVersion;
    }

    @Override
    public ResourceTypeMap getResourceTypeMap() {
        return resourceTypeMap;
    }

    @Override
    public void addResource(final ResourceInfo resource) {
    }

    @Override
    public void removeResource(final Integer id) {
    }

    @Override
    public FunctionMap getFunctionMap() {
        return new FunctionMap();
    }

    @Override
    public FunctionReturn callFunction(final FunctionCall functionCall) {
        halt(400);
        return null;
    }

    @Override
    public Status assignServer(final ServerInfo serverInfo) {
        try {
            Claims claims = ProcessorUtils.validateToken(serverInfo.getToken());
            String baseUrl = claims.getIssuer();
            Integer processorId = (Integer) claims.get("processorId");

            Server server = serverMap.get(serverInfo.getToken());
            if (server == null) {
                server = new Server(serverInfo.getToken(), baseUrl, processorId);
                serverMap.put(server.getToken(), server);
            } else {
                if (!server.getBaseUrl().equals(baseUrl) || !server.getProcessorId().equals(processorId)) {
                    halt(403, "Forbidden: server parameter mismatch.");
                }
            }

            return new Status(
                dataVersion,
                protocolVersion,
                new LinkedList<>()
            );
        } catch (JwtException | IllegalArgumentException e) {
            halt(403, "Forbidden: invalid token.");
        }

        return null;
    }

    @Override
    public void unassignServer(final ServerInfo serverInfo) {
        try {
            Claims claims = ProcessorUtils.validateToken(serverInfo.getToken());
            String baseUrl = claims.getIssuer();
            Integer processorId = (Integer) claims.get("processorId");

            Server server = serverMap.get(serverInfo.getToken());
            if (server != null) {
                if (!server.getBaseUrl().equals(baseUrl) || !server.getProcessorId().equals(processorId)) {
                    halt(403, "Forbidden: server parameter mismatch.");
                }

                serverMap.remove(server.getToken());
            }
        } catch (JwtException | IllegalArgumentException e) {
            halt(403, "Forbidden: invalid token.");
        }
    }

    @Override
    public void changeToken(final ChangeToken changeToken) {
        if (changeToken == null ||
            changeToken.getOldToken() == null || changeToken.getOldToken().isEmpty() ||
            changeToken.getNewToken() == null || changeToken.getNewToken().isEmpty() ||
            changeToken.getOldToken().equals(changeToken.getNewToken())
        ) {
            halt(400);
        }

        Server server = serverMap.get(changeToken.getOldToken());
        if (server == null) {
            halt(403, "Forbidden: server parameter mismatch.");
        }

        try {
            Claims claims = ProcessorUtils.validateToken(changeToken.getNewToken());
            String baseUrl = claims.getIssuer();
            Integer processorId = (Integer) claims.get("processorId");

            if (!server.getProcessorId().equals(processorId)) {
                halt(403, "Forbidden: server parameter mismatch.");
            }

            server.setBaseUrl(baseUrl);
            server.setToken(changeToken.getNewToken());
            serverMap.put(server.getToken(), server);
        } catch (JwtException | IllegalArgumentException e) {
            halt(403, "Forbidden: invalid token.");
        }
    }

    public static class Server {
        private String token;
        private String baseUrl;
        private Integer processorId;

        Server(final String token, final String baseUrl, final Integer processorId) {
            this.token = token;
            this.baseUrl = baseUrl;
            this.processorId = processorId;
        }

        String getToken() {
            return token;
        }

        void setToken(final String token) {
            this.token = token;
        }

        String getBaseUrl() {
            return baseUrl;
        }

        void setBaseUrl(final String baseUrl) {
            this.baseUrl = baseUrl;
        }

        Integer getProcessorId() {
            return processorId;
        }

        void setProcessorId(final Integer processorId) {
            this.processorId = processorId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(token, baseUrl, processorId);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final Server server = (Server) o;
            return Objects.equals(token, server.token) &&
                Objects.equals(baseUrl, server.baseUrl) &&
                Objects.equals(processorId, server.processorId);
        }
    }
}
