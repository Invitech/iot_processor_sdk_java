package hu.invitech.insight.processor.sdk.v1.server;

import java.util.Objects;

/**
 * Represents the information required for a token change on an assigned server.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ChangeToken {
    /**
     * The old token that needs to be changed.
     *
     * @since version 1
     */
    private String oldToken;

    /**
     * The new token that will replace the old one.
     *
     * @since version 1
     */
    private String newToken;

    /**
     * <p>Constructor for ChangeToken.</p>
     */
    public ChangeToken() {
    }

    /**
     * <p>Constructor for ChangeToken.</p>
     *
     * @param oldToken the old token that needs to be changed.
     * @param newToken the new token that will replace the old one.
     */
    public ChangeToken(final String oldToken, final String newToken) {
        this.oldToken = oldToken;
        this.newToken = newToken;
    }

    /**
     * <p>Getter for the field <code>oldToken</code>.</p>
     *
     * @return {@link #oldToken}
     */
    public String getOldToken() {
        return oldToken;
    }

    /**
     * <p>Setter for the field <code>oldToken</code>.</p>
     *
     * @param oldToken {@link hu.invitech.insight.processor.sdk.v1.server.ChangeToken#oldToken}
     */
    public void setOldToken(final String oldToken) {
        this.oldToken = oldToken;
    }

    /**
     * <p>Getter for the field <code>newToken</code>.</p>
     *
     * @return {{@link hu.invitech.insight.processor.sdk.v1.server.ChangeToken#newToken}
     */
    public String getNewToken() {
        return newToken;
    }

    /**
     * <p>Setter for the field <code>newToken</code>.</p>
     *
     * @param newToken {@link hu.invitech.insight.processor.sdk.v1.server.ChangeToken#newToken}
     */
    public void setNewToken(final String newToken) {
        this.newToken = newToken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(oldToken, newToken);
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
        final ChangeToken that = (ChangeToken) o;
        return Objects.equals(oldToken, that.oldToken) &&
            Objects.equals(newToken, that.newToken);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ChangeToken{" +
            "oldToken='" + oldToken + '\'' +
            ", newToken='" + newToken + '\'' +
            '}';
    }

}
