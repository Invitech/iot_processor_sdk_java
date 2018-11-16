package hu.invitech.insight.processor.sdk.v1.resource;

import hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the information about an available resource type.
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ResourceTypeInfo {
    /**
     * Name of the resource type.
     *
     * <p>
     * This will be used to identify the resource type when a new resource is added.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#type
     * @since version 1
     */
    private String name;

    /**
     * Required parameters for adding a resource of this type.
     *
     * <p>
     * The map should not be <code>null</code>, but it can be empty if there are no parameters.
     * </p>
     *
     * <ul>
     *   <li>
     *     <code>Key</code>: <code>name</code>/<code>id</code> of a parameter
     *   </li>
     *   <li>
     *     <code>Value</code>: description of the parameter (see {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue})
     *   </li>
     * </ul>
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#parameters
     *
     * @since version 1
     */
    private Map<String, TypedDefaultValue> parameters = new LinkedHashMap<>();

    /**
     * Incoming data that can be recieved from a resource of this type.
     *
     * <ul>
     *   <li>
     *     <code>Key</code>: key used to identify an incoming data type
     *   </li>
     *   <li>
     *     <code>Value</code>: description of the top-level incoming data (see {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo})
     *   </li>
     * </ul>
     *
     * @see hu.invitech.insight.processor.sdk.v1.data.IncomingData#data
     *
     * @since version 1
     */
    private Map<String, Collection<IncomingDataInfo>> incomingData = new LinkedHashMap<>();

    /**
     * <p>Constructor for ResourceTypeInfo.</p>
     */
    public ResourceTypeInfo() {
    }

    /**
     * <p>Constructor for ResourceTypeInfo.</p>
     *
     * @param name the name of the resource type.
     */
    public ResourceTypeInfo(final String name) {
        this.name = name;
    }

    /**
     * <p>Constructor for ResourceTypeInfo.</p>
     *
     * @param name the name of the resource type.
     * @param parameters the required parameters for adding a resource of this type.
     * @param incomingData the incoming data that can be recieved from a resource of this type.
     */
    public ResourceTypeInfo(final String name, final Map<String, TypedDefaultValue> parameters, final Map<String, Collection<IncomingDataInfo>> incomingData) {
        this.name = name;
        this.parameters = parameters;
        this.incomingData = incomingData;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#name}
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#name}
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#parameters}
     */
    public Map<String, TypedDefaultValue> getParameters() {
        return parameters;
    }

    /**
     * <p>Setter for the field <code>parameters</code>.</p>
     *
     * @param parameters {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#parameters}
     */
    public void setParameters(final Map<String, TypedDefaultValue> parameters) {
        this.parameters = parameters;
    }

    /**
     * <p>Getter for the field <code>incomingData</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#incomingData}
     */
    public Map<String, Collection<IncomingDataInfo>> getIncomingData() {
        return incomingData;
    }

    /**
     * <p>Setter for the field <code>incomingData</code>.</p>
     *
     * @param incomingData {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#incomingData}
     */
    public void setIncomingData(final Map<String, Collection<IncomingDataInfo>> incomingData) {
        this.incomingData = incomingData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, parameters, incomingData);
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
        final ResourceTypeInfo that = (ResourceTypeInfo) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(parameters, that.parameters) &&
            Objects.equals(incomingData, that.incomingData);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ResourceTypeInfo{" +
            "name='" + name + '\'' +
            ", parameters=" + parameters +
            ", incomingData=" + incomingData +
            '}';
    }

}
