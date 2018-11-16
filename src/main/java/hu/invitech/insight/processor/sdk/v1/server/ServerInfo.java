package hu.invitech.insight.processor.sdk.v1.server;

import java.util.Objects;

/**
 * Represents the information used to assign/unassign a server.
 *
 * @since version 1
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ServerInfo {
    /**
     * JWT bearer token used for communication between the processor and the assigned server in both directions.
     *
     * <p>
     * The token is specified like this:
     * </p>
     * <ul>
     *   <li>
     *   the subject is "processor"
     *   </li>
     *   <li>
     *   the issuer is the <code>baseUrl</code> of the server where the processor can access the interface provided by the platform
     *   </li>
     *   <li>
     *   the claims contain a <code>processorId</code> that can be used to identify this processor on the server
     *   </li>
     *   <li>
     *   the token is signed with RSA256 and can be validated with {@link hu.invitech.insight.processor.sdk.v1.util.ApiConstants#JWT_PUBLIC_KEY}
     *   </li>
     * </ul>
     *
     * @see hu.invitech.insight.processor.sdk.v1.util.ProcessorUtils#validateToken(String)
     * @since version 1
     */
    private String token;

    /**
     * <p>Constructor for ServerInfo.</p>
     */
    public ServerInfo() {
    }

    /**
     * <p>Constructor for ServerInfo.</p>
     *
     * @param token the JWT bearer token used for communication between the processor and the assigned server in both directions.
     */
    public ServerInfo(final String token) {
        this.token = token;
    }

    /**
     * <p>Getter for the field <code>token</code>.</p>
     *
     * @return {@link hu.invitech.insight.processor.sdk.v1.server.ServerInfo#token}
     */
    public String getToken() {
        return token;
    }

    /**
     * <p>Setter for the field <code>token</code>.</p>
     *
     * @param token {@link hu.invitech.insight.processor.sdk.v1.server.ServerInfo#token}
     */
    public void setToken(final String token) {
        this.token = token;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(token);
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
        final ServerInfo that = (ServerInfo) o;
        return Objects.equals(token, that.token);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "ServerInfo{" +
            "token='" + token + '\'' +
            '}';
    }

}
