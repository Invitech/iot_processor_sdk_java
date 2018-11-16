package hu.invitech.insight.processor.sdk.v1.resource;

/**
 * Represents the predefined sensor types that can be used to identify the data type of an {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo}.
 *
 * @see hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#dataType
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public class SensorTypes {
    /**
     * Temperature in degrees Celsius.
     *
     * @since version 1
     */
    public static final String TEMPERATURE = "temperature";

    /**
     * Percentage of relative humidity.
     *
     * @since version 1
     */
    public static final String HUMIDITY = "humidity";

    /**
     * Atmospheric pressure in Pa.
     *
     * @since version 1
     */
    public static final String PRESSURE = "pressure";

    /**
     * Battery charge percentage.
     *
     * @since version 1
     */
    public static final String BATTERY_PERCENTAGE = "charge";

    /**
     * Signal strength.
     *
     * @since version 1
     */
    public static final String SIGNAL_STRENGTH = "rssi";
}
