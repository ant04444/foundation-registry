package net.basiccloud.registry.filter;

import net.basiccloud.registry.ServiceInstance;

/**
 * Service instance filter.
 */
public interface ServiceInstanceFilter {

    String getFilterName();

    boolean filter(ServiceInstance instance);
}
