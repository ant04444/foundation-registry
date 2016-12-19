package net.basiccloud.registry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkModeTest {
    @Test
    public void isAvailable() throws Exception {
        assertThat(WorkMode.NORMAL.isAvailable()).isTrue();
        assertThat(WorkMode.MAINTENANCE.isAvailable()).isFalse();
        assertThat(WorkMode.DEGRADE.isAvailable()).isTrue();
    }

}