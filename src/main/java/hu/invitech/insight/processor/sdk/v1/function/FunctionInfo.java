package hu.invitech.insight.processor.sdk.v1.function;

import hu.invitech.insight.processor.sdk.v1.data.DataType;
import hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents information about an available function.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class FunctionInfo {
    /**
     * Name of the function.
     *
     * <p>
     * This will be used to identify the function when called.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionCall#functionName
     * @since version 1
     */
    private String name;

    /**
     * Required parameters of the function.
     *
     * <p>
     * The map should not be <code>null</code>, but it can be empty if there are no parameters.
     * </p>
     *
     * <ul>
     *   <li>
     *     <code>Key</code>: key used to identify the parameter
     *   </li>
     *   <li>
     *     <code>Value</code>: description of the parameter (see {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue})
     *   </li>
     * </ul>
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionCall#parameters
     *
     * @since version 1
     */
    private Map<String, TypedDefaultValue> parameters = new LinkedHashMap<>();

    /**
     * Return values of the function.
     *
     * <p>
     * The map should not be null, but it can be empty if there is no return value.
     * </p>
     *
     * <ul>
     *   <li>
     *     <code>Key</code>: <code>name</code>/<code>id</code> of the return value
     *   </li>
     *   <li>
     *     <code>Value</code>: {@link hu.invitech.insight.processor.sdk.v1.data.DataType} of the returned value
     *   </li>
     * </ul>
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionReturn#data
     *
     * @since version 1
     */
    private Map<String, DataType> returnValues = new LinkedHashMap<>();

    /**
     * <p>Constructor for FunctionInfo.</p>
     */
    public FunctionInfo() {
    }

    /**
     * <p>Constructor for FunctionInfo.</p>
     *
     * @param name the name of the function.
     */
    public FunctionInfo(final String name) {
        this.name = name;
    }

    /**
     * <p>Constructor for FunctionInfo.</p>
     *
     * @param name the name of the function.
     * @param parameters the parameters of the function.
     * @param returnValues the return values of the function.
     */
    public FunctionInfo(final String name, final Map<String, TypedDefaultValue> parameters, final Map<String, DataType> returnValues) {
        this.name = name;
        this.parameters = parameters;
        this.returnValues = returnValues;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#name}
     */
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name {@link hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#name}
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#parameters}
     */
    public Map<String, TypedDefaultValue> getParameters() {
        return parameters;
    }

    /**
     * <p>Setter for the field <code>parameters</code>.</p>
     *
     * @param parameters {@link hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#parameters}
     */
    public void setParameters(final Map<String, TypedDefaultValue> parameters) {
        this.parameters = parameters;
    }

    /**
     * <p>Getter for the field <code>returnValues</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#returnValues}
     */
    public Map<String, DataType> getReturnValues() {
        return returnValues;
    }

    /**
     * <p>Setter for the field <code>returnValues</code>.</p>
     *
     * @param returnValues {@link hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#returnValues}
     */
    public void setReturnValues(final Map<String, DataType> returnValues) {
        this.returnValues = returnValues;
    }

    /**
     * Adds a new parameter.
     *
     * @param key the key of the parameter
     * @param value the type of the parameter
     */
    public void addParameter(String key, TypedDefaultValue value) {
        parameters.put(key, value);
    }

    /**
     * Adds a new return value.
     *
     * @param key the key of the return value
     * @param value the type of the return value
     */
    public void addReturnValue(String key, DataType value) {
        returnValues.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, parameters, returnValues);
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
        final FunctionInfo that = (FunctionInfo) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(parameters, that.parameters) &&
            Objects.equals(returnValues, that.returnValues);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "FunctionInfo{" +
            "name='" + name + '\'' +
            ", parameters=" + parameters +
            ", returnValues=" + returnValues +
            '}';
    }

}
