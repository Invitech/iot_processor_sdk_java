package hu.invitech.insight.processor.sdk.v1.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.invitech.insight.processor.sdk.v1.data.JsonDataType;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents the information about incoming data produced by a resource.
 *
 * <p>
 * There are currently two basic types of accepted data for this class:
 * </p>
 *
 * <ol>
 *   <li>
 *     <code>dataType</code> is <code>OBJECT</code>, children is not empty, <code>sensorType</code> is <code>null</code>
 *   </li>
 *   <li>
 *     <code>dataType</code> is not <code>OBJECT</code>, children is empty, <code>sensorType</code> is not empty
 *   </li>
 * </ol>
 *
 * <p>
 * If neither of these are true, the data will be ignored. Any other data in the described tree will still be validated and used.
 * </p>
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class IncomingDataInfo {
    /**
     * The key used to identify this data.
     *
     * @see hu.invitech.insight.processor.sdk.v1.data.IncomingData#type
     *
     * @since version 1
     */
    private String key;

    /**
     * The type of this data.
     *
     * <p>
     * If this is not <code>OBJECT</code>, children has to be empty.
     * If this is <code>OBJECT</code>, <code>sensorType</code> has to be empty.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.data.JsonDataType
     *
     * @since version 1
     */
    private JsonDataType dataType;

    /**
     * Optional sensor type that defines how to handle this data.
     *
     * <p>
     * If not empty, <code>dataType</code> cannot be <code>OBJECT</code> and children has to be empty.
     * </p>
     *
     * <p>
     * Data from the same sensor type will always be grouped together.
     * Can be one of the constants from {@link hu.invitech.insight.processor.sdk.v1.resource.SensorTypes} or any other custom <code>String</code>.
     * </p>
     *
     * @since version 1
     */
    private String sensorType;

    /**
     * Optional list of possible values.
     *
     * <p>
     * If populated, only these values are accepted.
     * </p>
     *
     * <p>
     * The strings should contain data that can be parsed according to the supplied {@link #dataType}
     * </p>
     *
     * @since version 1
     */
    private Collection<String> possibleValues;

    /**
     * Optional data validation information.
     *
     * <p>
     * If supplied, the received data will be considered invalid if any of these constraints fail.
     * Invalid data will still be stored, but it won't be considered for alerts or shown on graphs.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo
     *
     * @since version 1
     */
    private ValidationInfo validationInfo;

    /**
     * Optional parent of this data. Can be used to define a tree of objects.
     *
     * @since version 1
     */
    @JsonIgnore
    private IncomingDataInfo parent;

    /**
     * Optional children of this data.
     *
     * <p>
     * Can be used to define a tree of objects.
     * If not empty, <code>dataType</code> has to be <code>OBJECT</code> and <code>sensorType</code> has to be <code>null</code>.
     * </p>
     *
     * @since version 1
     */
    private Set<IncomingDataInfo> children = new LinkedHashSet<>();

    /**
     * <p>Constructor for IncomingDataInfo.</p>
     */
    public IncomingDataInfo() {
    }

    /**
     * <p>Constructor for IncomingDataInfo.</p>
     *
     * @param key the key used to identify this data.
     * @param dataType the type of this data.
     * @param sensorType the sensor type that defines how to handle this data.
     * @param possibleValues an optional list of possible values.
     * @param validationInfo an optional data validation information.
     */
    public IncomingDataInfo(
        final String key,
        final JsonDataType dataType,
        final String sensorType,
        final Collection<String> possibleValues,
        final ValidationInfo validationInfo
    ) {
        this.key = key;
        this.dataType = dataType;
        this.sensorType = sensorType;
        this.possibleValues = possibleValues;
        this.validationInfo = validationInfo;
    }

    /**
     * <p>Getter for the field <code>key</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#key}
     */
    public String getKey() {
        return key;
    }

    /**
     * <p>Setter for the field <code>key</code>.</p>
     *
     * @param key {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#key}
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * <p>Getter for the field <code>dataType</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#dataType}
     */
    public JsonDataType getDataType() {
        return dataType;
    }

    /**
     * <p>Setter for the field <code>dataType</code>.</p>
     *
     * @param dataType {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#dataType}
     */
    public void setDataType(final JsonDataType dataType) {
        this.dataType = dataType;
    }

    /**
     * <p>Getter for the field <code>sensorType</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#sensorType}
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * <p>Setter for the field <code>sensorType</code>.</p>
     *
     * @param sensorType {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#sensorType}
     */
    public void setSensorType(final String sensorType) {
        this.sensorType = sensorType;
    }

    /**
     * <p>Getter for the field <code>possibleValues</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#possibleValues}
     */
    public Collection<String> getPossibleValues() {
        return possibleValues;
    }

    /**
     * <p>Setter for the field <code>possibleValues</code>.</p>
     *
     * @param possibleValues {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#possibleValues}
     */
    public void setPossibleValues(final Collection<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    /**
     * <p>Getter for the field <code>validationInfo</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#validationInfo}
     */
    public ValidationInfo getValidationInfo() {
        return validationInfo;
    }

    /**
     * <p>Setter for the field <code>validationInfo</code>.</p>
     *
     * @param validationInfo {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#validationInfo}
     */
    public void setValidationInfo(final ValidationInfo validationInfo) {
        this.validationInfo = validationInfo;
    }

    /**
     * <p>Getter for the field <code>parent</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#parent}
     */
    public IncomingDataInfo getParent() {
        return parent;
    }

    /**
     * <p>Setter for the field <code>parent</code>.</p>
     *
     * @param parent {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#parent}
     */
    public void setParent(final IncomingDataInfo parent) {
        this.parent = parent;
    }

    /**
     * <p>Getter for the field <code>children</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#children}
     */
    public Set<IncomingDataInfo> getChildren() {
        return children;
    }

    /**
     * <p>Setter for the field <code>children</code>.</p>
     *
     * @param children {@link hu.invitech.insight.processor.sdk.v1.resource.IncomingDataInfo#children}
     */
    public void setChildren(final Set<IncomingDataInfo> children) {
        this.children = children;
    }

    /**
     * Adds a new child.
     *
     * @param child the child to be added
     */
    public void addChild(final IncomingDataInfo child) {
        child.setParent(this);
        children.add(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(key);
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
        final IncomingDataInfo that = (IncomingDataInfo) o;
        return Objects.equals(key, that.key);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "IncomingDataInfo{" +
            "key='" + key + '\'' +
            ", dataType=" + dataType +
            ", sensorType='" + sensorType + '\'' +
            ", possibleValues=" + possibleValues +
            ", validationInfo=" + validationInfo +
            '}';
    }
}
