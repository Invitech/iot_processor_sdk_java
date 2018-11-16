package hu.invitech.insight.processor.sdk.v1.resource;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * Represents the description of all the available resource types.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ResourceTypeMap {
    /**
     * Collection of all available resource type descriptions supported by this processor.
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeInfo
     * @since version 1
     */
    private Collection<ResourceTypeInfo> resourceTypes = new LinkedHashSet<>();

    /**
     * <p>Constructor for ResourceTypeMap.</p>
     */
    public ResourceTypeMap() {
    }

    /**
     * <p>Constructor for ResourceTypeMap.</p>
     *
     * @param resourceTypes a collection of all available resource type descriptions supported by this processor.
     */
    public ResourceTypeMap(final Collection<ResourceTypeInfo> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    /**
     * <p>Getter for the field <code>resourceTypes</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeMap#resourceTypes}
     */
    public Collection<ResourceTypeInfo> getResourceTypes() {
        return resourceTypes;
    }

    /**
     * <p>Setter for the field <code>resourceTypes</code>.</p>
     *
     * @param resourceTypes {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeMap#resourceTypes}
     */
    public void setResourceTypes(final Collection<ResourceTypeInfo> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(resourceTypes);
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
        final ResourceTypeMap that = (ResourceTypeMap) o;
        return Objects.equals(resourceTypes, that.resourceTypes);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ResourceTypeMap{" +
            "resourceTypes=" + resourceTypes +
            '}';
    }

}
