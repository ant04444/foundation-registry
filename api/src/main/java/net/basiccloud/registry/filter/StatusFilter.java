package net.basiccloud.registry.filter;

import net.basiccloud.registry.ServiceInstance;

/**
 * Status filter for service instance.
 */
public class StatusFilter extends AbstractServiceInstanceFilter {
    public static class Builder {

    }

    public static Builder newBuilder() {
        return new Builder();
    }


    private StatusFilter() {
        super("StatusFilter");
    }


    @Override
    public boolean filter(ServiceInstance instance) {
        return false;
    }


}
