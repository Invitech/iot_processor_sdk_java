package hu.invitech.insight.processor.example.api;

import hu.invitech.insight.processor.example.ManualInputExample;
import hu.invitech.insight.processor.example.data.Resource;
import hu.invitech.insight.processor.example.data.Server;
import hu.invitech.insight.processor.sdk.v1.api.ServerInterface;
import hu.invitech.insight.processor.sdk.v1.server.ChangeToken;
import hu.invitech.insight.processor.sdk.v1.server.ServerInfo;
import hu.invitech.insight.processor.sdk.v1.server.Status;
import hu.invitech.insight.processor.sdk.v1.util.ProcessorUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static spark.Spark.halt;

public class ServerApi implements ServerInterface {
    @Override
    public Status assignServer(final ServerInfo serverInfo) {
        try {
            Claims claims = ProcessorUtils.validateToken(serverInfo.getToken());
            String baseUrl = claims.getIssuer();
            Integer processorId = (Integer) claims.get("processorId");

            String jxQuery = String.format("/.[token='%s']", serverInfo.getToken());
            List<Server> serverList = ManualInputExample.jsonDb.find(jxQuery, Server.class);
            Server server;
            if (serverList == null || serverList.isEmpty()) {
                server = new Server(UUID.randomUUID().toString(), serverInfo.getToken(), baseUrl, processorId);
                ManualInputExample.jsonDb.insert(server);
            } else {
                server = serverList.get(0);
                if (!server.getBaseUrl().equals(baseUrl) || !server.getProcessorId().equals(processorId)) {
                    halt(403);
                }
            }

            return new Status(
                ManualInputExample.dataVersion,
                ManualInputExample.protocolVersion,
                new LinkedList<>()
            );
        } catch (JwtException | IllegalArgumentException e) {
            halt(403);
        }

        return null;
    }

    @Override
    public void unassignServer(final ServerInfo serverInfo) {
        try {
            Claims claims = ProcessorUtils.validateToken(serverInfo.getToken());
            String baseUrl = claims.getIssuer();
            Integer processorId = (Integer) claims.get("processorId");

            String jxQuery = String.format("/.[token='%s']", serverInfo.getToken());
            List<Server> serverList = ManualInputExample.jsonDb.find(jxQuery, Server.class);
            Server server;
            if (serverList != null && !serverList.isEmpty()) {
                server = serverList.get(0);
                if (!server.getBaseUrl().equals(baseUrl) || !server.getProcessorId().equals(processorId)) {
                    halt(403);
                }

                ManualInputExample.jsonDb.remove(server, Server.class);

                String resourceQuery = String.format("/.[serverToken='%s']", server.getToken());
                ManualInputExample.jsonDb.remove(ManualInputExample.jsonDb.find(resourceQuery, Resource.class), Resource.class);
            }
        } catch (JwtException | IllegalArgumentException e) {
            halt(403);
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

        String serverQuery = String.format("/.[token='%s']", changeToken.getOldToken());
        List<Server> serverList = ManualInputExample.jsonDb.find(serverQuery, Server.class);
        Server server;
        if (serverList == null || serverList.isEmpty()) {
            halt(403);
            return;
        } else {
            server = serverList.get(0);
        }

        try {
            Claims claims = ProcessorUtils.validateToken(changeToken.getNewToken());
            String baseUrl = claims.getIssuer();
            Integer processorId = (Integer) claims.get("processorId");

            if (!server.getProcessorId().equals(processorId)) {
                halt(403);
            }

            server.setToken(changeToken.getNewToken());
            server.setBaseUrl(baseUrl);
            ManualInputExample.jsonDb.upsert(server);

            String resourceQuery = String.format("/.[serverToken='%s']", changeToken.getOldToken());
            List<Resource> resourceList = ManualInputExample.jsonDb.find(resourceQuery, Resource.class);
            for (Resource resource : resourceList) {
                resource.setServerToken(changeToken.getNewToken());
                ManualInputExample.jsonDb.upsert(resource);
            }
        } catch (JwtException | IllegalArgumentException e) {
            halt(403);
        }
    }
}
