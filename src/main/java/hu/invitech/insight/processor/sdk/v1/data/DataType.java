package hu.invitech.insight.processor.sdk.v1.data;

/**
 * Represents all available data types for function parameters, function return values and resource type parameters.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public enum DataType {
    /**
     * Textual value type.
     *
     * @since version 1
     */
    STRING,

    /**
     * Integer number type.
     *
     * <p>
     * Note: the precision is not defined, it can either an {@link java.lang.Integer} or a {@link java.lang.Long}
     * </p>
     *
     * @since version 1
     */
    INTEGER,

    /**
     * Floating point number type.
     *
     * <p>
     * Note: the precision is not defined, it can be either a {@link java.lang.Float} or a {@link java.lang.Double}
     * </p>
     *
     * @since version 1
     */
    FLOAT,

    /**
     * Boolean type.
     *
     * @since version 1
     */
    BOOLEAN
}
