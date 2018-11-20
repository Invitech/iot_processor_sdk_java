package hu.invitech.insight.processor.example.data;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
@Document(collection = "servers", schemaVersion = "1.0")
public class Server {
    @Id
    private String id;
    private String token;
    private String baseUrl;
    private Integer processorId;

    public Server() {
    }

    public Server(final String id, final String token, final String baseUrl, final Integer processorId) {
        this.id = id;
        this.token = token;
        this.baseUrl = baseUrl;
        this.processorId = processorId;
    }

    public Server(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getProcessorId() {
        return processorId;
    }

    public void setProcessorId(final Integer processorId) {
        this.processorId = processorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        return Objects.equals(id, server.id);
    }

    @Override
    public String toString() {
        return "Server{" +
            "id='" + id + '\'' +
            ", token='" + token + '\'' +
            ", baseUrl='" + baseUrl + '\'' +
            ", processorId=" + processorId +
            '}';
    }
}
