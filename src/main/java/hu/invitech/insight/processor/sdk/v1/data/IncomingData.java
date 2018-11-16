package hu.invitech.insight.processor.sdk.v1.data;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Date;
import java.util.Objects;

/**
 * Represents incoming data received from a resource.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class IncomingData {
    /**
     * Optional <code>id</code> of the received data, mainly for deduplication purposes.
     *
     * @since version 1
     */
    private String id;

    /**
     * Id of the resource which produced this data.
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#id
     * @since version 1
     */
    private Integer resourceId;

    /**
     * Reference to the key of {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#incomingData} which maps the specification of this data.
     *
     * @since version 1
     */
    private String type;

    /**
     * Data receive timestamp.
     *
     * @since version 1
     */
    private Date timestamp = new Date();

    /**
     * The actual incoming data in a JSON object.
     *
     * <p>
     * Needs to conform to the specification defined in {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo}
     * </p>
     *
     * @since version 1
     */
    private ObjectNode data;

    /**
     * <p>Constructor for IncomingData.</p>
     */
    public IncomingData() {
    }

    /**
     * <p>Constructor for IncomingData.</p>
     *
     * @param id an optional <code>id</code> of the received data, mainly for deduplication purposes.
     * @param resourceId the id of the resource which produced this data.
     * @param type a reference to the {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#key} used to define this data.
     * @param data the actual incoming data in a JSON object.
     */
    public IncomingData(final String id, final Integer resourceId, final String type, final ObjectNode data) {
        this.id = id;
        this.resourceId = resourceId;
        this.type = type;
        this.data = data;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#id}
     */
    public String getId() {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#id}
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * <p>Getter for the field <code>resourceId</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#resourceId}
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * <p>Setter for the field <code>resourceId</code>.</p>
     *
     * @param resourceId {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#resourceId}
     */
    public void setResourceId(final Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#type}
     */
    public String getType() {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#type}
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * <p>Getter for the field <code>timestamp</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#timestamp}
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * <p>Setter for the field <code>timestamp</code>.</p>
     *
     * @param timestamp {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#timestamp}
     */
    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * <p>Getter for the field <code>data</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#data}
     */
    public ObjectNode getData() {
        return data;
    }

    /**
     * <p>Setter for the field <code>data</code>.</p>
     *
     * @param data {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#data}
     */
    public void setData(final ObjectNode data) {
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(resourceId, type, timestamp, data);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IncomingData that = (IncomingData) o;
        return Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(type, that.type) &&
            Objects.equals(timestamp, that.timestamp) &&
            Objects.equals(data, that.data);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "IncomingData{" +
            ", resourceId=" + resourceId +
            ", type='" + type + '\'' +
            ", timestamp=" + timestamp +
            ", data=" + data +
            '}';
    }

}
