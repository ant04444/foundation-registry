package net.basiccloud.registry;

import org.junit.Test;

public class ServiceTest {

    private final String groupName = "Group1";
    private final String serviceName = "Service1";

    @Test
    public void newBuilder() throws Exception {
        ServiceInstanceData data = ServiceInstanceData.newBuilder()
                .setServiceVersion("1.0.1")
                .setFrameworkVersion("1.0.0")
                .addTag("Demo", "Test")
                .addHttpPort(1081)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
        ServiceInstanceStatus status = ServiceInstanceStatus.newBuilder().asOnline().build();

        ServiceInstance serviceInstance = ServiceInstance.newBuilder().setGroup(groupName)
                .setService(serviceName)
                .setIp("192.168.0.1")
                .setPort(1080)
                .setData(data)
                .setStatus(status)
                .build();

        Service service = Service.newBuilder().setGroup(groupName).setService(serviceName)
                .setInfo(ServiceInfo.newBuilder().setDisplayName("Service One").build())
                .addInstance(serviceInstance)
                .build();
        //System.out.println(setService);
    }

}