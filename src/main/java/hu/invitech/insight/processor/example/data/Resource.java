package hu.invitech.insight.processor.example.data;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

import java.util.Objects;

@SuppressWarnings({"unused", "WeakerAccess"})
@Document(collection = "resources", schemaVersion = "1.0")
public class Resource {
    @Id
    private String id;
    private String type;
    private String serverToken;
    private Integer originalId;

    public Resource() {
    }

    public Resource(final String id, final String type, final String serverToken, final Integer originalId) {
        this.id = id;
        this.type = type;
        this.serverToken = serverToken;
        this.originalId = originalId;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getServerToken() {
        return serverToken;
    }

    public void setServerToken(final String serverToken) {
        this.serverToken = serverToken;
    }

    public Integer getOriginalId() {
        return originalId;
    }

    public void setOriginalId(final Integer originalId) {
        this.originalId = originalId;
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
        final Resource resource = (Resource) o;
        return Objects.equals(id, resource.id);
    }

    @Override
    public String toString() {
        return "Resource{" +
            "id='" + id + '\'' +
            ", type='" + type + '\'' +
            ", serverToken='" + serverToken + '\'' +
            ", originalId=" + originalId +
            '}';
    }
}
