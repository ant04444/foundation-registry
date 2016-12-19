package net.basiccloud.registry;


import java.util.List;

/**
 * client of setService registry.
 */
public interface ServiceRegistryClient {

    List<ServiceInstance> discover(String group, String service);

    List<ServiceInstance> discover(String group, String service, Version version);

    boolean watch(String group, String service, Version version, ClientListener clientListener);

    interface ClientListener {
        void update(List<ServiceInstance> list);
    }
}
