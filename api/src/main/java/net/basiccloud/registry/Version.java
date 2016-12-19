package net.basiccloud.registry;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * version definition.
 *
 * <p>Defines the version for setService or others.
 */
public class Version {

    private static final Pattern VERSION_REGEX = Pattern.compile("^\\d{1,3}(\\.\\d{1,3}){0,2}$");

    /**
     * exception of invalid version.
     */
    public static class InvalidVersionException extends RuntimeException {
        public InvalidVersionException(String msg) {
            super(msg);
        }
    }

    /**
     * major version.
     */
    private final int major;
    /**
     * minor version.
     */
    private final int minor;
    /**
     * patch version.
     */
    private final int patch;

    /**
     * constructor with specified major，minor and patch
     *
     * @param major major of version，{@code >= 0 && <= 999}
     * @param minor minor of version，{@code >= 0 && <= 999}
     * @param patch patch of version，{@code  >= 0 && <= 999 }
     * @throws IllegalArgumentException if one of major/minor/patch is invalid
     */
    public Version(int major, int minor, int patch) {
        checkArgument(major >= 0 && major <= 999, "major of version should between 0 and 999: current major=" + major);
        checkArgument(minor >= 0 && minor <= 999, "minor of version should between 0 and 999: current minor=" + minor);
        checkArgument(patch >= 0 && patch <= 999, "patch of version should between 0 and 999: current patch=" + patch);

        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * parse from string.
     * @param version version in string format like "1.0.0"
     * @return version object
     * @throws InvalidVersionException if version is invalid
     */
    public static Version valueOf(String version) {
        checkNotNull(version, "version should not be null");

        if (!VERSION_REGEX.matcher(version).matches()) {
            throw new InvalidVersionException("Invalid version, " +
                    "it should like 1/1.0/1.0.0: version = " + version);
        }

        String[] parts = version.split("\\.");
        int major;
        int minor = 0;
        int patch = 0;
        if (parts.length == 1) {
            major = Integer.valueOf(parts[0]);
        } else if (parts.length == 2) {
            major = Integer.valueOf(parts[0]);
            minor = Integer.valueOf(parts[1]);
        } else {
            major = Integer.valueOf(parts[0]);
            minor = Integer.valueOf(parts[1]);
            patch = Integer.valueOf(parts[2]);
        }

        return new Version(major, minor, patch);
    }

    /**
     * check if current version is back compatible with given version.
     *
     * @param targetVersion target version
     * @return true if back compatible with target version, false if not
     */
    public boolean isBackCompatibleWith(Version targetVersion) {
        // 1. compare major first
        if (this.major != targetVersion.major) {
            // major must equal, otherwise return false directly
            // For example: if server is 2.*, then the client should also be 2.*
            return false;
        }

        // 2. same major, continue to compare the minor version
        if (this.minor < targetVersion.minor) {
            // 2.1 if minor is less than target version, return false
            // For example: if server is 2.5.*, and client is 2.6.*, it should be incompatible.
            // In this case, patch comparison is not required.
            return false;
        }

        if (this.minor > targetVersion.minor) {
            // 2.2 if minor is greater than target version, return true
            // For example: if server is 2.5.*, and client is 2.4.*/2.3.*, it should be compatible.
            // In this case, patch comparison is not required.
            return true;
        }

        // 2.3 major and minor are both the same, we need to compare patch
        // if (this.minor == targetVersion.minor) { }
        return this.patch >= targetVersion.patch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Version version = (Version) o;

        if (major != version.major) return false;
        if (minor != version.minor) return false;
        return patch == version.patch;

    }

    @Override
    public int hashCode() {
        int result = major;
        result = 31 * result + minor;
        result = 31 * result + patch;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(16);
        builder.append(this.major).append('.').append(this.minor).append('.').append(this.patch);
        return builder.toString();
    }
}
