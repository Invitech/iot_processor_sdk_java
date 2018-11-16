package hu.invitech.insight.processor.sdk.v1.function;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the returned values from a function.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class FunctionReturn {
    /**
     * The actual data map returned from a function.
     *
     * <p>
     * The map should not be <code>null</code>, but it can be empty if there is no return value.
     * </p>
     *
     * @see hu.invitech.insight.processor.sdk.v1.function.FunctionInfo#returnValues
     *
     * @since version 1
     */
    private Map<String, String> data = new LinkedHashMap<>();

    /**
     * <p>Constructor for FunctionReturn.</p>
     */
    public FunctionReturn() {
    }

    /**
     * <p>Constructor for FunctionReturn.</p>
     *
     * @param data the actual data map returned from a function.
     */
    public FunctionReturn(final Map<String, String> data) {
        this.data = data;
    }

    /**
     * <p>Getter for the field <code>data</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.function.FunctionReturn#data}
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * <p>Setter for the field <code>data</code>.</p>
     *
     * @param data {@link hu.invitech.insight.processor.sdk.v1.function.FunctionReturn#data}
     */
    public void setData(final Map<String, String> data) {
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(data);
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
        final FunctionReturn that = (FunctionReturn) o;
        return Objects.equals(data, that.data);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "FunctionReturn{" +
            "data=" + data +
            '}';
    }

}
