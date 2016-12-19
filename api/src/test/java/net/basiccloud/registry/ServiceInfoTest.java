package net.basiccloud.registry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceInfoTest {
    @Test
    public void newBuilder() throws Exception {
        ServiceInfo serviceInfo = ServiceInfo.newBuilder().setDisplayName("User Service").build();
        //System.out.println(serviceInfo);
        assertThat(serviceInfo.getDisplayName()).isEqualTo("User Service");
    }

    @Test
    public void newBuilder_all() throws Exception {
        ServiceInfo serviceInfo = ServiceInfo.newBuilder()
                .setDisplayName("User Service")
                .setDescription("user setService in Department1")
                .build();
        //System.out.println(serviceInfo);
        assertThat(serviceInfo.getDisplayName()).isEqualTo("User Service");
    }

    @Test
    public void newBuilder_withData() throws Exception {
        ServiceInfo serviceInfo1 = ServiceInfo.newBuilder()
                .setDisplayName("User Service")
                .setDescription("user setService in Department1")
                .build();
        ServiceInfo serviceInfo2 = ServiceInfo.newBuilder(serviceInfo1).build();
        assertThat(serviceInfo1.getDisplayName()).isEqualTo(serviceInfo2.getDisplayName());
        assertThat(serviceInfo1.getDescription()).isEqualTo(serviceInfo2.getDescription());

        ServiceInfo serviceInfo3 = ServiceInfo.newBuilder(serviceInfo1)
                .setDisplayName("UserService-New")
                .setDescription("user setService in DepartmentB")
                .build();
        assertThat(serviceInfo3.getDisplayName()).isEqualTo("UserService-New");
        assertThat(serviceInfo3.getDescription()).isEqualTo("user setService in DepartmentB");
    }
}