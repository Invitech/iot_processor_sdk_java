package hu.invitech.insight.processor.sdk.core.api;

/**
 * Provides a service for retrieving the latest supported protocol version of the processor.
 *
 * <p>
 * This service should never change and needs to be accessible at the following path:
 * {@value hu.invitech.insight.processor.sdk.core.util.CoreApiConstants#CORE_PATH}
 * {@value hu.invitech.insight.processor.sdk.core.util.CoreApiConstants#VERSION_PATH}
 * </p>
 *
 * @version 1
 * @author kavaleczm
 */
public interface VersionInterface {
    /**
     * Returns the latest supported protocol version of the processor.
     *
     * <p>
     * Should respond to the following request: GET /
     * </p>
     *
     * @return the latest supported protocol version of the processor
     */
    Integer getVersion();
}
