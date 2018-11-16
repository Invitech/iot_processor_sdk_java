package hu.invitech.insight.processor.sdk.v1.resource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the information required to add a new resource to the processor.
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ResourceInfo {
    /**
     * Id of the resource that can be used to refer to it on the server.
     *
     * <p>
     * This most likely won't be the same as the <code>id</code> used to refer to this resource on the processor, because one processor can be connected to multiple servers.
     * </p>
     *
     * @since version 1
     */
    private Integer id;

    /**
     * Resource type name.
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#name
     * @since version 1
     */
    private String type;

    /**
     * Parameters of the resource.
     *
     * <p>
     * The map should not be <code>null</code>, but it can be empty if there are no parameters.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo#parameters
     *
     * @since version 1
     */
    private Map<String, String> parameters = new LinkedHashMap<>();

    /**
     * <p>Constructor for ResourceInfo.</p>
     */
    public ResourceInfo() {
    }

    /**
     * <p>Constructor for ResourceInfo.</p>
     *
     * @param id the id of the resource that can be used to refer to it on the server.
     * @param type the resource type name.
     */
    public ResourceInfo(final Integer id, final String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * <p>Constructor for ResourceInfo.</p>
     *
     * @param id the id of the resource that can be used to refer to it on the server.
     * @param type the resource type name.
     * @param parameters the parameters of the resource.
     */
    public ResourceInfo(final Integer id, final String type, final Map<String, String> parameters) {
        this.id = id;
        this.type = type;
        this.parameters = parameters;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#id}
     */
    public Integer getId() {
        return id;
    }

    /**
     * <p>Setter for the field <code>id</code>.</p>
     *
     * @param id {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#id}
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#type}
     */
    public String getType() {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#type}
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#parameters}
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * <p>Setter for the field <code>parameters</code>.</p>
     *
     * @param parameters {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#parameters}
     */
    public void setParameters(final Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Adds a new parameter.
     *
     * @param key the key of the parameter
     * @param value the value of the parameter
     */
    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, type, parameters);
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
        final ResourceInfo that = (ResourceInfo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(type, that.type) &&
            Objects.equals(parameters, that.parameters);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ResourceInfo{" +
            "id=" + id +
            ", type='" + type + '\'' +
            ", parameters=" + parameters +
            '}';
    }

}
