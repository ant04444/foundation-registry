package net.basiccloud.registry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceGroupInfoTest {
    @Test
    public void newBuilder() throws Exception {
        ServiceGroupInfo groupInfo = ServiceGroupInfo.newBuilder().setDisplayName("CoreGroup").build();
        //System.out.println(groupInfo);
        assertThat(groupInfo.getDisplayName()).isEqualTo("CoreGroup");
    }

    @Test
    public void newBuilder_all() throws Exception {
        ServiceGroupInfo groupInfo = ServiceGroupInfo.newBuilder()
                .setDisplayName("CoreGroup")
                .setDepartment("DepartmentA")
                .setDescription("user setService in Department1")
                .build();
        //System.out.println(groupInfo);
        assertThat(groupInfo.getDisplayName()).isEqualTo("CoreGroup");
        assertThat(groupInfo.getDepartment()).isEqualTo("DepartmentA");
    }

    @Test
    public void newBuilder_withData() throws Exception {
        ServiceGroupInfo groupInfo1 = ServiceGroupInfo.newBuilder()
                .setDisplayName("CoreGroup")
                .setDepartment("DepartmentA")
                .setDescription("CoreGroup in Department1")
                .build();
        ServiceGroupInfo groupInfo2 = ServiceGroupInfo.newBuilder(groupInfo1).build();
        assertThat(groupInfo1.getDisplayName()).isEqualTo(groupInfo2.getDisplayName());
        assertThat(groupInfo1.getDepartment()).isEqualTo(groupInfo2.getDepartment());
        assertThat(groupInfo1.getDescription()).isEqualTo(groupInfo2.getDescription());

        ServiceGroupInfo groupInfo3 = ServiceGroupInfo.newBuilder(groupInfo1)
                .setDisplayName("CoreGroup-New")
                .setDepartment("DepartmentB")
                .setDescription("CoreGroup in DepartmentB")
                .build();
        assertThat(groupInfo3.getDisplayName()).isEqualTo("CoreGroup-New");
        assertThat(groupInfo3.getDepartment()).isEqualTo("DepartmentB");
        assertThat(groupInfo3.getDescription()).isEqualTo("CoreGroup in DepartmentB");
    }
}