package hu.invitech.insight.processor.sdk.v1.api;

import hu.invitech.insight.processor.sdk.v1.function.FunctionCall;
import hu.invitech.insight.processor.sdk.v1.function.FunctionMap;
import hu.invitech.insight.processor.sdk.v1.function.FunctionReturn;

/**
 * Provides a service for getting information about available resource functions and calling functions on managed resources.
 *
 * <p>
 * This service needs to be accessible at the following path:
 * /v1
 * {@value hu.invitech.insight.processor.sdk.v1.util.ApiConstants#FUNCTION_PATH}
 * </p>
 *
 * @author kavaleczm
 * @version 1
 */
@SuppressWarnings("unused")
public interface FunctionInterface {
    /**
     * Returns the description of available functions for each resource type.
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
     * @return the description of available functions mapped by resource type (see {@link hu.invitech.insight.processor.sdk.v1.function.FunctionMap})
     *
     * @since version 1
     */
    FunctionMap getFunctionMap();

    /**
     * Calls a function on a resource with the supplied parameters.
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
     * <td>A function with the name in {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#functionName} doesn't exist</td>
     * </tr>
     * <tr>
     * <td>404</td>
     * <td>Not Found</td>
     * <td>A resource with the id in  {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall#resourceId} doesn't exist</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>Internal Server Error</td>
     * <td>The function call encountered an unrecoverable error</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>OK</td>
     * <td>OK</td>
     * </tr>
     * </table>
     *
     * @param functionCall the function call description (see {@link hu.invitech.insight.processor.sdk.v1.function.FunctionCall})
     *
     * @return the return values of the function (see {@link hu.invitech.insight.processor.sdk.v1.function.FunctionReturn})
     *
     * @since version 1
     */
    FunctionReturn callFunction(FunctionCall functionCall);
}
