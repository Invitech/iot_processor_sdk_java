package hu.invitech.insight.processor.sdk.v1.resource;

import java.util.Objects;

/**
 * Represents data validation information
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ValidationInfo {
    /**
     * Minimum value for validating numbers.
     *
     * <p>
     * A number will be considered valid if it's greater than or equal to this value.
     * </p>
     *
     * @since version 1
     */
    private Double min;

    /**
     * Maximum value for validating numbers.
     *
     * <p>
     * A number will be considered valid if it's less than or equal to this value.
     * </p>
     *
     * @since version 1
     */
    private Double max;

    /**
     * Regex for validating strings.
     *
     * <p>
     * Needs to be in a format supported by {@link java.lang.String#matches(String)}.
     * </p>
     *
     * @since version 1
     */
    private String regex;

    /**
     * <p>Constructor for ValidationInfo.</p>
     */
    public ValidationInfo() {
    }

    /**
     * <p>Constructor for ValidationInfo.</p>
     *
     * @param min a minimum value for validating numbers.
     * @param max a maximum value for validating numbers.
     */
    public ValidationInfo(final Double min, final Double max) {
        this.min = min;
        this.max = max;
    }

    /**
     * <p>Constructor for ValidationInfo.</p>
     *
     * @param regex a regex for validating strings.
     */
    public ValidationInfo(final String regex) {
        this.regex = regex;
    }

    /**
     * <p>Constructor for ValidationInfo.</p>
     *
     * @param min a minimum value for validating numbers.
     * @param max a maximum value for validating numbers.
     * @param regex a regex for validating strings.
     */
    public ValidationInfo(final Double min, final Double max, final String regex) {
        this.min = min;
        this.max = max;
        this.regex = regex;
    }

    /**
     * <p>Getter for the field <code>min</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo#min}
     */
    public Double getMin() {
        return min;
    }

    /**
     * <p>Setter for the field <code>min</code>.</p>
     *
     * @param min {@link hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo#min}
     */
    public void setMin(final Double min) {
        this.min = min;
    }

    /**
     * <p>Getter for the field <code>max</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo#max}
     */
    public Double getMax() {
        return max;
    }

    /**
     * <p>Setter for the field <code>max</code>.</p>
     *
     * @param max {@link hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo#max}
     */
    public void setMax(final Double max) {
        this.max = max;
    }

    /**
     * <p>Getter for the field <code>regex</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo#regex}
     */
    public String getRegex() {
        return regex;
    }

    /**
     * <p>Setter for the field <code>regex</code>.</p>
     *
     * @param regex {@link hu.invitech.insight.processor.sdk.v1.resource.ValidationInfo#regex}
     */
    public void setRegex(final String regex) {
        this.regex = regex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(min, max, regex);
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
        final ValidationInfo that = (ValidationInfo) o;
        return Objects.equals(min, that.min) &&
            Objects.equals(max, that.max) &&
            Objects.equals(regex, that.regex);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ValidationInfo{" +
            "min=" + min +
            ", max=" + max +
            ", regex='" + regex + '\'' +
            '}';
    }
}
