package hu.invitech.insight.processor.sdk.v1.api;

import hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo;
import hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeMap;

/**
 * Provides a service for getting information about available resource types and adding/removing managed resources.
 *
 * <p>
 * This service needs to be accessible at the following path:
 * /v1
 * {@value hu.invitech.insight.processor.sdk.v1.util.ApiConstants#RESOURCE_PATH}
 * </p>
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public interface ResourceInterface {
    /**
     * Returns the description of the available resource types.
     *
     * <p>
     * Needs JWT bearer authorization and should return HTTP 401 if the token is missing or invalid.
     * </p>
     *
     * <p>
     * Should be accessible at the following path: GET /
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
     * <td>The token is not assigned or it was previously assigned to a different server than the caller</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>OK</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @return the description of the available resource types (see {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceTypeMap})
     *
     * @since version 1
     */
    ResourceTypeMap getResourceTypeMap();

    /**
     * Adds a new resource with the supplied parameters.
     *
     * <p>
     * Needs JWT bearer authorization and should return HTTP 401 if the token is missing or invalid.
     * </p>
     *
     * <p>
     * Should be accessible at the following path: POST /
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
     * <td>The token is not assigned or it was previously assigned to a different server than the caller</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>Bad Request</td>
     * <td>A resource type with a name in {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#type} doesn't exist</td>
     * </tr>
     * <tr>
     * <td>409</td>
     * <td>Conflict</td>
     * <td>A resource with an id in {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo#id} already exists</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>Internal Server Error</td>
     * <td>An unrecoverable error occured while adding the resource</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param resource the information about the added resource (see {@link hu.invitech.insight.processor.sdk.v1.resource.ResourceInfo})
     *
     * @since version 1
     */
    void addResource(ResourceInfo resource);

    /**
     * Removes the resource with the given <code>id</code>.
     *
     * <p>
     * Needs JWT bearer authorization and should return HTTP 401 if the token is missing or invalid.
     * </p>
     *
     * <p>
     * Should be accessible at the following path: DELETE /
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
     * <td>The token is not assigned or it was previously assigned to a different server than the caller</td>
     * </tr>
     * <tr>
     * <td>404</td>
     * <td>Not Found</td>
     * <td>a resource with this id doesn't exist</td>
     * </tr>
     * <tr>
     * <td>204</td>
     * <td>No Content</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param id the id of the resource to be deleted
     *
     * @since version 1
     */
    void removeResource(Integer id);
}
