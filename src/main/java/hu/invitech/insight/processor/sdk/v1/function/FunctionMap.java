package hu.invitech.insight.processor.sdk.v1.function;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the description of all the available functions for each resource type.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class FunctionMap {
    /**
     * Description of available functions for each resource type supported by this processor.
     *
     * <ul>
     *   <li>
     *     <code>Key</code>: name of a resource type
     *   </li>
     *   <li>
     *     <code>Value</code>: collection of defined functions for a resource type
     *   </li>
     * </ul>
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionInfo
     * @since version 1
     */
    private Map<String, Collection<FunctionInfo>> resourceTypeFunctions = new LinkedHashMap<>();

    /**
     * <p>Constructor for FunctionMap.</p>
     */
    public FunctionMap() {
    }

    /**
     * <p>Constructor for FunctionMap.</p>
     *
     * @param resourceTypeFunctions the description of available functions for each resource type supported by this processor.
     */
    public FunctionMap(final Map<String, Collection<FunctionInfo>> resourceTypeFunctions) {
        this.resourceTypeFunctions = resourceTypeFunctions;
    }

    /**
     * <p>Getter for the field <code>resourceTypeFunctions</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionMap#resourceTypeFunctions}
     */
    public Map<String, Collection<FunctionInfo>> getResourceTypeFunctions() {
        return resourceTypeFunctions;
    }

    /**
     * <p>Setter for the field <code>resourceTypeFunctions</code>.</p>
     *
     * @param resourceTypeFunctions {@link hu.invitech.insight.processor.sdk.v1.function.FunctionMap#resourceTypeFunctions}
     */
    public void setResourceTypeFunctions(final Map<String, Collection<FunctionInfo>> resourceTypeFunctions) {
        this.resourceTypeFunctions = resourceTypeFunctions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(resourceTypeFunctions);
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
        final FunctionMap that = (FunctionMap) o;
        return Objects.equals(resourceTypeFunctions, that.resourceTypeFunctions);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "FunctionMap{" +
            "resourceTypeFunctions=" + resourceTypeFunctions +
            '}';
    }

}
