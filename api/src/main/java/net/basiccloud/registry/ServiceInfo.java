package net.basiccloud.registry;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Service information.
 */
public class ServiceInfo {

    public static class Builder {

        private String displayName;
        private String description;

        private Builder() {

        }

        public Builder setDisplayName(String displayName) {
            checkArgument(!Strings.isNullOrEmpty(displayName), "displayName should not be null or empty");
            this.displayName = displayName.trim();
            return this;
        }

        public Builder setDescription(String description) {
            this.description = Strings.emptyToNull(description);
            return this;
        }

        public ServiceInfo build() {
            checkState(displayName != null, "please set displayName before build()");

            return new ServiceInfo(displayName, Strings.nullToEmpty(description));
        }
    }

    /**
     * create a new empty builder.
     *
     * @return a new empty builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * create a new builder with serviceInfo.
     *
     * @param serviceInfo the information to be added to the new builder
     * @return a new builder with given information
     */
    public static Builder newBuilder(ServiceInfo serviceInfo) {
        return new Builder().setDisplayName(serviceInfo.getDisplayName())
                .setDescription(serviceInfo.getDescription());
    }

    private final String displayName;
    private final String description;

    private ServiceInfo(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ServiceGroupInfo{" +
                "setDisplayName='" + displayName + '\'' +
                ", setDescription='" + description + '\'' +
                '}';
    }
}
