package hu.invitech.insight.processor.sdk.v1.api;

import hu.invitech.insight.processor.sdk.v1.server.ChangeToken;
import hu.invitech.insight.processor.sdk.v1.server.ServerInfo;
import hu.invitech.insight.processor.sdk.v1.server.Status;

/**
 * Provides a service for assigning/unassigning servers, and changing tokens of assigned servers.
 *
 * <p>
 * This service needs to be accessible at the following path:
 * /v1
 * {@value hu.invitech.insight.processor.sdk.v1.util.ApiConstants#SERVER_PATH}
 * </p>
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public interface ServerInterface {
    /**
     * Assigns a server to the processor with the given token.
     *
     * <p>
     * Should be accessible at the following path: POST {@value hu.invitech.insight.processor.sdk.v1.util.ApiConstants#ASSIGN_SERVER_PATH}
     * </p>
     *
     * <table>
     * <caption>HTTP status codes</caption>
     * <tr>
     * <td>Code</td>
     * <td>Text</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>403</td>
     * <td>Forbidden</td>
     * <td>The token is empty or invalid</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>OK</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param serverInfo the information about the server to be assigned (see {@link hu.invitech.insight.processor.sdk.v1.server.ServerInfo})
     *
     * @return the current processor status related to this server (see {@link hu.invitech.insight.processor.sdk.v1.server.Status})
     *
     * @since version 1
     */
    Status assignServer(ServerInfo serverInfo);

    /**
     * Unassigns a server from the processor with the given token.
     *
     * <p>
     * Should be accessible at the following path: POST {@value hu.invitech.insight.processor.sdk.v1.util.ApiConstants#UNASSIGN_SERVER_PATH}
     * </p>
     *
     * <table>
     * <caption>HTTP status codes</caption>
     * <tr>
     * <td>Code</td>
     * <td>Text</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>403</td>
     * <td>Forbidden</td>
     * <td>The token is empty, invalid, or assigned to a different server than the caller</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param serverInfo the information about the server to be unassigned (see {@link hu.invitech.insight.processor.sdk.v1.server.ServerInfo})
     *
     * @since version 1
     */
    void unassignServer(ServerInfo serverInfo);

    /**
     * Changes the token of an assigned server.
     *
     * <p>
     * Should be accessible at the following path: POST {@value hu.invitech.insight.processor.sdk.v1.util.ApiConstants#CHANGE_TOKEN_PATH}
     * </p>
     *
     * <table>
     * <caption>HTTP status codes</caption>
     * <tr>
     * <td>Code</td>
     * <td>Text</td>
     * <td>Description</td>
     * </tr>
     * <tr>
     * <td>403</td>
     * <td>Forbidden</td>
     * <td>The new token is empty, invalid, unassigned, or assigned to a different server than the caller</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>Bad Request</td>
     * <td>The old or the new token is missing or they are both the same</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param changeToken the information required for the token change (see {@link hu.invitech.insight.processor.sdk.v1.server.ChangeToken})
     *
     * @since version 1
     */
    void changeToken(ChangeToken changeToken);
}
