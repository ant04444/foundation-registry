package net.basiccloud.registry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceInstanceTest {
    @Test
    public void newBuilder() throws Exception {
        ServiceInstanceData data = ServiceInstanceData.newBuilder()
                .setServiceVersion("1.0.1")
                .setFrameworkVersion("1.0.0")
                .addTag("Demo", "Test")
                .addHttpPort(1080)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
        ServiceInstanceStatus status = ServiceInstanceStatus.newBuilder().asOnline().build();

        ServiceInstance serviceInstance = ServiceInstance.newBuilder().setGroup("Main")
                .setService("UserService")
                .setIp("192.168.0.1")
                .setPort(1080)
                .setData(data)
                .setStatus(status)
                .build();
        //System.out.println(serviceInstance);
        assertThat(serviceInstance.getGroup()).isEqualTo("Main");
    }

    @Test
    public void newBuilder_withData() throws Exception {
        ServiceInstanceData data = ServiceInstanceData.newBuilder()
                .setServiceVersion("1.0.1")
                .setFrameworkVersion("1.0.0")
                .addTag("Demo", "Test")
                .addHttpPort(1080)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
        ServiceInstanceStatus status = ServiceInstanceStatus.newBuilder().asOnline().build();

        ServiceInstance instance1 = ServiceInstance.newBuilder().setGroup("Main")
                .setService("UserService")
                .setIp("192.168.0.1")
                .setPort(1080)
                .setData(data)
                .setStatus(status)
                .build();
        //System.out.println(serviceInstance);
        ServiceInstance instance2 = ServiceInstance.newBuilder(instance1).build();
        assertThat(instance1.getGroup()).isEqualTo(instance2.getGroup());
        assertThat(instance1.getService()).isEqualTo(instance2.getService());
        assertThat(instance1.getIp()).isEqualTo(instance2.getIp());
        assertThat(instance1.getPort()).isEqualTo(instance2.getPort());

        ServiceInstance instance3 = ServiceInstance.newBuilder(instance1)
                .setGroup("Main2")
                .build();
        assertThat(instance3.getGroup()).isEqualTo("Main2");
    }
}