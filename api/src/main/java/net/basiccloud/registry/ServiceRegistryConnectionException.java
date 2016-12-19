package net.basiccloud.registry;

/**
 * ServiceRegistry connection exception.
 */
public class ServiceRegistryConnectionException extends RuntimeException {

    public ServiceRegistryConnectionException(String message) {
        super(message);
    }

    public ServiceRegistryConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
