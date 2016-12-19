package net.basiccloud.registry;

/**
 * server of setService registry.
 */
public interface ServiceRegistryServer<R extends RegisterId> {

    /**
     * register setService.
     *
     * @param serviceInstance setService instance
     * @return lease id
     */
    R register(ServiceInstance serviceInstance);

    /**
     * deregister setService.
     *
     * @param registerId register Id returned in register() method
     */
    void deregister(R registerId);
}
