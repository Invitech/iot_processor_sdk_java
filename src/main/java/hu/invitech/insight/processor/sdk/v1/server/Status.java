package hu.invitech.insight.processor.sdk.v1.server;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the current status of the processor.
 *
 * <p>
 * Used for synchronizing data between the processor and the server.
 * </p>
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Status {
    /**
     * The data version of the processor.
     *
     * <p>
     * Needs to start from <code>1</code> and has to be incremented every time there's a change in the returned data from
     * {@link hu.invitech.insight.processor.sdk.v1.api.ResourceInterface#getResourceTypeMap()} or {@link hu.invitech.insight.processor.sdk.v1.api.FunctionInterface#getFunctionMap()}.
     * </p>
     *
     * @since version 1
     */
    private Integer dataVersion;

    /**
     * The protocol version of the processor.
     *
     * @since version 1
     */
    private Integer protocolVersion;

    /**
     * The resource <code>id</code>-s currently managed by this processor, for a specific server.
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#id
     * @see hu.invitech.insight.processor.sdk.v1.api.ServerInterface#assignServer(ServerInfo)
     * @since version 1
     */
    private List<Integer> managedResourceIds = new LinkedList<>();

    /**
     * <p>Constructor for Status.</p>
     */
    public Status() {
    }

    /**
     * <p>Constructor for Status.</p>
     *
     * @param dataVersion the data version of the processor.
     * @param protocolVersion the protocol version of the processor.
     * @param managedResourceIds the resource <code>id</code>-s currently managed by this processor, for a specific server.
     */
    public Status(Integer dataVersion, Integer protocolVersion, final List<Integer> managedResourceIds) {
        this.dataVersion = dataVersion;
        this.protocolVersion = protocolVersion;
        this.managedResourceIds = managedResourceIds;
    }

    /**
     * <p>Getter for the field <code>dataVersion</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.server.Status#dataVersion}
     */
    public Integer getDataVersion() {
        return dataVersion;
    }

    /**
     * <p>Setter for the field <code>dataVersion</code>.</p>
     *
     * @param dataVersion {@link hu.invitech.insight.processor.sdk.v1.server.Status#dataVersion}
     */
    public void setDataVersion(final Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public Integer getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(final Integer protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * <p>Getter for the field <code>managedResourceIds</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.server.Status#managedResourceIds}
     */
    public List<Integer> getManagedResourceIds() {
        return managedResourceIds;
    }

    /**
     * <p>Setter for the field <code>managedResourceIds</code>.</p>
     *
     * @param managedResourceIds {@link hu.invitech.insight.processor.sdk.v1.server.Status#managedResourceIds}
     */
    public void setManagedResourceIds(final List<Integer> managedResourceIds) {
        this.managedResourceIds = managedResourceIds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(dataVersion, protocolVersion, managedResourceIds);
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
        final Status status = (Status) o;
        return Objects.equals(dataVersion, status.dataVersion) &&
            Objects.equals(protocolVersion, status.protocolVersion) &&
            Objects.equals(managedResourceIds, status.managedResourceIds);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Status{" +
            "dataVersion=" + dataVersion +
            ", protocolVersion=" + protocolVersion +
            ", managedResourceIds=" + managedResourceIds +
            '}';
    }

}
