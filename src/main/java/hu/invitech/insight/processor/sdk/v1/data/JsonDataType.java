package hu.invitech.insight.processor.sdk.v1.data;

/**
 * Represents all available data types for incoming data.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public enum JsonDataType {
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
     * Note: It will be parsed and stored as a {@link java.lang.Long} value
     * </p>
     *
     * @since version 1
     */
    INTEGER,

    /**
     * Floating point number type.
     *
     * <p>
     * Note: It will be parsed and stored as a {@link java.lang.Double} value
     * </p>
     *
     * @since version 1
     */
    DOUBLE,

    /**
     * Boolean type.
     *
     * <p>
     * Any of the following values can be accepted (case insensitive):
     * </p>
     * <ul>
     *   <li>
     *     <code>1</code> or <code>0</code>
     *   </li>
     *   <li>
     *     <code>yes</code> or <code>no</code>
     *   </li>
     *   <li>
     *     <code>on</code> or <code>off</code>
     *   </li>
     *   <li>
     *     <code>true</code> or <code>false</code>
     *   </li>
     * </ul>
     *
     * @since version 1
     */
    BOOLEAN,

    /**
     * JSON Object type.
     *
     * @since version 1
     */
    OBJECT,

    /**
     * Textual value type that contains a date in the format defined by {@link java.time.format.DateTimeFormatter#ISO_INSTANT}.
     *
     * @since version 1
     */
    DATE_STRING
}
