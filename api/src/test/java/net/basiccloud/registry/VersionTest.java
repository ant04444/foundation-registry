package net.basiccloud.registry;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class VersionTest {

    @Test
    public void testIsBackCompatibleWith() throws Exception {
        // test major
        Version clientVersion = Version.valueOf("2.0.0");
        String[] compatibleServerVersions = new String[]{"2.0.0"};
        String[] incompatibleServerVersions = new String[]{"1.0.0", "3.0.0", "4.0.0"};
        for(String serverVersion : compatibleServerVersions) {
            assertThat(Version.valueOf(serverVersion).isBackCompatibleWith(clientVersion)).isTrue();
        }
        for(String serverVersion : incompatibleServerVersions) {
            assertThat(Version.valueOf(serverVersion).isBackCompatibleWith(clientVersion)).isFalse();
        }

        // same major, test minor
        clientVersion = Version.valueOf("2.5.0");
        compatibleServerVersions = new String[]{"2.5.0","2.6.0","2.8.0","2.8.0"};
        incompatibleServerVersions = new String[]{"2.1.0", "2.2.0", "2.3.9", "2.4.6"};
        for(String serverVersion : compatibleServerVersions) {
            assertThat(Version.valueOf(serverVersion).isBackCompatibleWith(clientVersion)).isTrue();
        }
        for(String serverVersion : incompatibleServerVersions) {
            assertThat(Version.valueOf(serverVersion).isBackCompatibleWith(clientVersion)).isFalse();
        }

        // same major and minor, test patch
        clientVersion = Version.valueOf("2.5.5");
        compatibleServerVersions = new String[]{"2.5.5","2.5.6","2.8.18","2.9.0"};
        incompatibleServerVersions = new String[]{"2.5.4", "2.5.3", "2.3.9", "2.0.6"};
        for(String serverVersion : compatibleServerVersions) {
            assertThat(Version.valueOf(serverVersion).isBackCompatibleWith(clientVersion)).isTrue();
        }
        for(String serverVersion : incompatibleServerVersions) {
            assertThat(Version.valueOf(serverVersion).isBackCompatibleWith(clientVersion)).isFalse();
        }
    }

    @Test
    public void testToString() throws Exception {
        assertThat(new Version(0, 0, 0).toString()).isEqualTo("0.0.0");
        assertThat(new Version(0, 0, 1).toString()).isEqualTo("0.0.1");
        assertThat(new Version(1, 0, 0).toString()).isEqualTo("1.0.0");
        assertThat(new Version(2, 3, 5).toString()).isEqualTo("2.3.5");
        assertThat(new Version(100, 0, 123).toString()).isEqualTo("100.0.123");

        assertThat(Version.valueOf("1").toString()).isEqualTo("1.0.0");
        assertThat(Version.valueOf("2.1").toString()).isEqualTo("2.1.0");
    }

    @Test
    public void testValueOf() throws Exception {
        assertThat(Version.valueOf("0.0.0").toString()).isEqualTo("0.0.0");
        assertThat(Version.valueOf("0.0.1").toString()).isEqualTo("0.0.1");
        assertThat(Version.valueOf("1.0.0").toString()).isEqualTo("1.0.0");
        assertThat(Version.valueOf("2.3.5").toString()).isEqualTo("2.3.5");

        assertThat(Version.valueOf("1").toString()).isEqualTo("1.0.0");
        assertThat(Version.valueOf("2.1").toString()).isEqualTo("2.1.0");
    }

    @Test
    public void testValueOf_validFormat() throws Exception {
        String[] invalidInput = new String[]{"1.a", "1.", ".1", "1.1.1.", "1.1.1.234", "abc", "1. 1",
                "1.1.1.a", "1.1.a", "1.1.", " 1.1 ", ".1.1.1", "-1", "1.-1.0"};
        for (String invalid : invalidInput) {
            try {
                Version.valueOf(invalid);
                fail("it should not reach here");
            } catch (Version.InvalidVersionException e) {
                // OK
            }
        }
    }

    @Test
    public void testValueOf_tooBig() throws Exception {
        String[] invalidInput = new String[]{"1.0.1001", "1.1001.1", "1000.0.1"};
        for (String invalid : invalidInput) {
            try {
                Version.valueOf(invalid);
                fail("it should not reach here");
            } catch (Version.InvalidVersionException e) {
                // OK
            }
        }
    }

}