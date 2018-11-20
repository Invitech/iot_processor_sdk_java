package hu.invitech.insight.processor.sdk.v1.api;

import hu.invitech.insight.processor.sdk.v1.data.IncomingData;
import hu.invitech.insight.processor.sdk.v1.server.ServerInfo;
import hu.invitech.insight.processor.sdk.v1.server.Status;

/**
 * Provides a service for handling processor lifecycle and receiving data from resources.
 *
 * <p>
 * This service is accessible at the <code>baseUrl</code> provided in the server's token (see {@link hu.invitech.insight.processor.sdk.v1.api.ServerInterface#assignServer(ServerInfo)})
 * </p>
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public interface RemoteInterface {
    /**
     * Tells the server that this processor has started, and starts a data sync if needed.
     *
     * <p>
     * Needs JWT bearer authorization and returns HTTP 401 if the token is missing or invalid.
     * </p>
     *
     * <p>
     * Accessible at the following path: POST /v1
     * </p>
     *
     * <table class="invitechTable">
     * <caption>HTTP status codes</caption>
     * <tr>
     * <th>Code</th>
     * <th>Text</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>403</td>
     * <td>Forbidden</td>
     * <td>The token is not assigned or it is assigned to a different processor than the caller</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param status the current status of this processor in relation to the server on which this method is called (see {@link hu.invitech.insight.processor.sdk.v1.server.Status})
     *
     * @since version 1
     */
    void start(Status status);

    /**
     * Sends received incoming data from a resource to the server.
     *
     * <p>
     * Needs JWT bearer authorization and returns HTTP 401 if the token is missing or invalid.
     * </p>
     *
     * <p>
     * Accessible at the following path: POST /data/v1
     * </p>
     *
     * <table class="invitechTable">
     * <caption>HTTP status codes</caption>
     * <tr>
     * <th>Code</th>
     * <th>Text</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>403</td>
     * <td>Forbidden</td>
     * <td>The token is not assigned or it is assigned to a different processor than the caller</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>Bad Request</td>
     * <td>An incoming data type with the name in {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#type} doesn't exist</td>
     * </tr>
     * <tr>
     * <td>404</td>
     * <td>Not Found</td>
     * <td>A resource with the id in {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData#resourceId} doesn't exist</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param data the data received from a resource (see {@link hu.invitech.insight.processor.sdk.v1.data.IncomingData})
     *
     * @since version 1
     */
    void sendData(IncomingData data);

    /**
     * Tells the server that this processor has stopped.
     *
     * <p>
     * Needs JWT bearer authorization and returns HTTP 401 if the token is missing or invalid.
     * </p>
     *
     * <p>
     * Accessible at the following path: DELETE /v1
     * </p>
     *
     * <table class="invitechTable">
     * <caption>HTTP status codes</caption>
     * <tr>
     * <th>Code</th>
     * <th>Text</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>403</td>
     * <td>Forbidden</td>
     * <td>The token is not assigned or it is assigned to a different processor than the caller</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * ForbiddenException if the token is not assigned or it is assigned to a different processor than the caller
     *
     * @since version 1
     */
    void stop();
}
