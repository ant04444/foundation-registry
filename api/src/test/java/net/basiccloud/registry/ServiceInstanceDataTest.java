package net.basiccloud.registry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceInstanceDataTest {

    @Test
    public void newBuilder() throws Exception {
        ServiceInstanceData serviceInstanceData = ServiceInstanceData.newBuilder()
                .setServiceVersion("1.0.1")
                .setFrameworkVersion("1.0.0")
                .setWorkMode(WorkMode.MAINTENANCE)
                .addTag("Demo", "Test")
                .addHttpPort(1080)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
        //System.out.println(serviceInstanceData);
        assertThat(serviceInstanceData.getServiceVersion().toString()).isEqualTo("1.0.1");
        assertThat(serviceInstanceData.getFrameworkVersion().toString()).isEqualTo("1.0.0");
        assertThat(serviceInstanceData.getWorkMode()).isEqualTo(WorkMode.MAINTENANCE);
        assertThat(serviceInstanceData.getTags()).contains("Demo", "Test");
        assertThat(serviceInstanceData.getPorts().get(ServiceInstanceData.PROTOCOL_HTTP)).isEqualTo(1080);
        assertThat(serviceInstanceData.getPorts().get(ServiceInstanceData.PROTOCOL_HTTPS)).isEqualTo(1081);
    }


    @Test(expected = IllegalStateException.class)
    public void newBuilder_withoutServiceVersion() throws Exception {
        ServiceInstanceData.newBuilder()
                .setFrameworkVersion("1.0.0")
                .addTag("Demo", "Test")
                .addHttpPort(1080)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
    }

    @Test(expected = IllegalStateException.class)
    public void newBuilder_withoutFrameworkVersion() throws Exception {
        ServiceInstanceData.newBuilder()
                .setServiceVersion("1.0.1")
                .addTag("Demo", "Test")
                .addHttpPort(1080)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
    }

    @Test
    public void newBuilder_withStatus() {
        ServiceInstanceData data1 = ServiceInstanceData.newBuilder()
                .setServiceVersion("1.0.1")
                .setFrameworkVersion("1.0.0")
                .addTag("Demo", "Test")
                .addHttpPort(1080)
                .addPort(ServiceInstanceData.PROTOCOL_HTTPS, 1081)
                .build();
        ServiceInstanceData data2 = ServiceInstanceData.newBuilder(data1).build();
        assertThat(data1.getServiceType()).isEqualTo(data2.getServiceType());
        assertThat(data1.getServiceVersion()).isEqualTo(data2.getServiceVersion());
        assertThat(data1.getFrameworkVersion()).isEqualTo(data2.getFrameworkVersion());

        ServiceInstanceData status3 = ServiceInstanceData.newBuilder(data1).setServiceVersion("2.0.0").build();
        assertThat(status3.getServiceVersion().toString()).isEqualTo("2.0.0");
    }
}