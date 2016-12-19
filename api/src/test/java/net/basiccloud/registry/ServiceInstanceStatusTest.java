package net.basiccloud.registry;

import org.junit.Test;

import static net.basiccloud.registry.ServiceInstanceStatus.Status.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceInstanceStatusTest {

    @Test
    public void newBuilder() {
        ServiceInstanceStatus serviceInstanceStatus = ServiceInstanceStatus.newBuilder()
                .setStatus(ONLINE)
                .setLastUpdateTime(System.currentTimeMillis())
                .setComment("OK")
                .setLeaseId(1001)
                .build();
        //System.out.println(serviceInstanceStatus);
        assertThat(serviceInstanceStatus.isOnline()).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void newBuilder_withoutStatus() {
        ServiceInstanceStatus.newBuilder()
                .setLastUpdateTime(System.currentTimeMillis())
                .setComment("OK").build();
    }

    @Test
    public void newBuilder_asOnline() {
        ServiceInstanceStatus serviceInstanceStatus = ServiceInstanceStatus.newBuilder().asOnline().build();
        assertThat(serviceInstanceStatus.getStatus()).isEqualTo(ONLINE);
        assertThat(serviceInstanceStatus.isOnline()).isTrue();
    }

    @Test
    public void newBuilder_asOffline() {
        ServiceInstanceStatus serviceInstanceStatus = ServiceInstanceStatus.newBuilder().asOffline().build();
        assertThat(serviceInstanceStatus.getStatus()).isEqualTo(OFFLINE);
        assertThat(serviceInstanceStatus.isOffline()).isTrue();
    }

    @Test
    public void newBuilder_withStatus() {
        ServiceInstanceStatus status1 = ServiceInstanceStatus.newBuilder().asOffline().build();
        ServiceInstanceStatus status2 = ServiceInstanceStatus.newBuilder(status1).build();
        assertThat(status1.getStatus()).isEqualTo(status2.getStatus());
        assertThat(status1.getLastUpdateTime()).isEqualTo(status2.getLastUpdateTime());
        assertThat(status1.getComment()).isEqualTo(status2.getComment());

        ServiceInstanceStatus status3 = ServiceInstanceStatus.newBuilder(status1).asOffline().build();
        assertThat(status3.getStatus()).isEqualTo(OFFLINE);
    }
}