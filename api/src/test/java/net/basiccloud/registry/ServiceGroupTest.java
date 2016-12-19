package net.basiccloud.registry;

import org.junit.Test;

public class ServiceGroupTest {

    @Test
    public void newBuilder() throws Exception {
        ServiceGroup group = ServiceGroup.newBuilder().setGroup("Group1")
                .setInfo(ServiceGroupInfo.newBuilder().setDisplayName("Group One").build())
                .addService(Service.newBuilder().setGroup("Group1").setService("Service1")
                        .setInfo(ServiceInfo.newBuilder().setDisplayName("Service One").build()).build())
                .build();
        //System.out.println(setGroup);
    }

}