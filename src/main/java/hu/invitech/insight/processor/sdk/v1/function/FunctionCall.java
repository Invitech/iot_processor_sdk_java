package hu.invitech.insight.processor.sdk.v1.function;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the parameters required for calling functions.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class FunctionCall {
    /**
     * Id of the resource on which the function will be called.
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#id
     * @since version 1
     */
    private Integer resourceId;

    /**
     * Name of the function to be called.
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#name
     *
     * @since version 1
     */
    private String functionName;

    /**
     * Parameters of the function call.
     *
     * <p>
     * The map should not be <code>null</code>, but it can be empty if there are no parameters.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#parameters
     *
     * @since version 1
     */
    private Map<String, String> parameters = new LinkedHashMap<>();

    /**
     * <p>Constructor for FunctionCall.</p>
     */
    public FunctionCall() {
    }

    /**
     * <p>Constructor for FunctionCall.</p>
     *
     * @param resourceId the id of the resource on which the function will be called.
     * @param functionName the name of the function to be called.
     */
    public FunctionCall(final Integer resourceId, final String functionName) {
        this.resourceId = resourceId;
        this.functionName = functionName;
    }

    /**
     * <p>Constructor for FunctionCall.</p>
     *
     * @param resourceId the id of the resource on which the function will be called.
     * @param functionName the name of the function to be called.
     * @param parameters the parameters of the function call.
     */
    public FunctionCall(final Integer resourceId, final String functionName, final Map<String, String> parameters) {
        this.resourceId = resourceId;
        this.functionName = functionName;
        this.parameters = parameters;
    }

    /**
     * <p>Getter for the field <code>resourceId</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#resourceId}
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * <p>Setter for the field <code>resourceId</code>.</p>
     *
     * @param resourceId {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#resourceId}
     */
    public void setResourceId(final Integer resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * <p>Getter for the field <code>functionName</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#functionName}
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * <p>Setter for the field <code>functionName</code>.</p>
     *
     * @param functionName {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#functionName}
     */
    public void setFunctionName(final String functionName) {
        this.functionName = functionName;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#parameters}
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * <p>Setter for the field <code>parameters</code>.</p>
     *
     * @param parameters {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#parameters}
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
        return Objects.hash(resourceId, functionName, parameters);
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
        final FunctionCall that = (FunctionCall) o;
        return Objects.equals(resourceId, that.resourceId) &&
            Objects.equals(functionName, that.functionName) &&
            Objects.equals(parameters, that.parameters);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "FunctionCall{" +
            "resourceId='" + resourceId + '\'' +
            ", functionName='" + functionName + '\'' +
            ", parameters=" + parameters +
            '}';
    }

}
