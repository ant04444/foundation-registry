package net.basiccloud.registry;

/**
 * Connection for ServiceRegistry.
 */
public interface ServiceRegistryConnection {

    /**
     * connect to registry.
     *
     * @throws ServiceRegistryConnectionException if fails to connect to registry
     */
    void connect();
}
