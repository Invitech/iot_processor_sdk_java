package hu.invitech.insight.processor.sdk.v1.data;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents information about function and resource type parameters.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class TypedDefaultValue {
    /**
     * Expected data type.
     *
     * @see hu.invitech.insight.processor.sdk.v1.data.DataType
     * @since version 1
     */
    private DataType type;

    /**
     * Optional default value to be used in case the parameter is not supplied.
     *
     * <p>
     * The string should contain data that can be parsed according to the supplied {@link #type}
     * </p>
     *
     * @since version 1
     */
    private String defaultValue;

    /**
     * Optional collection of possible values.
     *
     * <p>
     * If populated, only these values are accepted.
     * The strings should contain data that can be parsed according to the supplied {@link #type}
     * </p>
     *
     * @since version 1
     */
    private Collection<String> possibleValues;

    /**
     * <p>Constructor for TypedDefaultValue.</p>
     */
    public TypedDefaultValue() {
    }

    /**
     * <p>Constructor for TypedDefaultValue.</p>
     *
     * @param type the expected data type.
     * @param defaultValue an optional default value to be used in case the parameter is not supplied.
     */
    public TypedDefaultValue(final DataType type, final String defaultValue) {
        this.type = type;
        this.defaultValue = defaultValue;
    }

    /**
     * <p>Constructor for TypedDefaultValue.</p>
     *
     * @param type the expected data type.
     * @param defaultValue an optional default value to be used in case the parameter is not supplied.
     * @param possibleValues an optional collection of possible values.
     */
    public TypedDefaultValue(final DataType type, final String defaultValue, final Collection<String> possibleValues) {
        this.type = type;
        this.defaultValue = defaultValue;
        this.possibleValues = possibleValues;
    }

    /**
     * <p>Getter for the field <code>type</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue#type}
     */
    public DataType getType() {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue#type}
     */
    public void setType(final DataType type) {
        this.type = type;
    }

    /**
     * <p>Getter for the field <code>defaultValue</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue#defaultValue}
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * <p>Setter for the field <code>defaultValue</code>.</p>
     *
     * @param defaultValue {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue#defaultValue}
     */
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * <p>Getter for the field <code>possibleValues</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue#possibleValues}
     */
    public Collection<String> getPossibleValues() {
        return possibleValues;
    }

    /**
     * <p>Setter for the field <code>possibleValues</code>.</p>
     *
     * @param possibleValues {@link hu.invitech.insight.processor.sdk.v1.data.TypedDefaultValue#possibleValues}
     */
    public void setPossibleValues(final Collection<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, defaultValue, possibleValues);
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
        final TypedDefaultValue that = (TypedDefaultValue) o;
        return type == that.type &&
            Objects.equals(defaultValue, that.defaultValue) &&
            Objects.equals(possibleValues, that.possibleValues);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "TypedDefaultValue{" +
            "type=" + type +
            ", defaultValue=" + defaultValue +
            ", possibleValues=" + possibleValues +
            '}';
    }
}
