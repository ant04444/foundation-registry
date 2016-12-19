package net.basiccloud.registry;

import java.util.Optional;

/**
 * admin of setService registry.
 */
public interface ServiceRegistryAdmin {

    /**
     * get setService instance by setGroup/setService/setIp/setPort.
     *
     * @param group setService setGroup
     * @param service setService
     * @param ip setIp address
     * @param port setPort
     * @return setService instance found, empty if no such instance
     */
    Optional<ServiceInstance> getInstance(String group, String service, String ip, int port);
}
