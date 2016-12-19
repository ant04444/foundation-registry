package net.basiccloud.registry;

import java.util.Map;

/**
 * factory of setService registry.
 */
public interface ServiceRegistryFactory {
    /**
     * default priority (10).
     */
    int PRIORITY_DEFAULT = 10;

    String getName();

    int getPriority();

    ServiceRegistryConnection connect(Map<String, String> parameters);

    ServiceRegistryClient getClient(ServiceRegistryConnection conn);

    ServiceRegistryServer getServer(ServiceRegistryConnection conn);

    ServiceRegistryAdmin getAdmin(ServiceRegistryConnection conn);
}
