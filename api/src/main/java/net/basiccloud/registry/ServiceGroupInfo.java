package net.basiccloud.registry;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * Service setGroup information.
 */
public class ServiceGroupInfo {

    public static class Builder {

        private String displayName;
        private String department;
        private String description;

        private Builder() {

        }

        public Builder setDisplayName(String displayName) {
            checkArgument(!Strings.isNullOrEmpty(displayName), "displayName should not be null or empty");
            this.displayName = displayName.trim();
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = Strings.emptyToNull(department);
            return this;
        }

        public Builder setDescription(String description) {
            this.description = Strings.emptyToNull(description);
            return this;
        }

        public ServiceGroupInfo build() {
            checkState(displayName != null, "please set setDisplayName before build()");

            return new ServiceGroupInfo(displayName, Strings.nullToEmpty(department), Strings.nullToEmpty(description));
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
     * create a new builder with groupInfo.
     * @param groupInfo the information to be added to the new builder
     * @return a new builder with given information
     */
    public static Builder newBuilder(ServiceGroupInfo groupInfo) {
        return new Builder().setDisplayName(groupInfo.getDisplayName())
                .setDepartment(groupInfo.getDepartment())
                .setDescription(groupInfo.getDescription());
    }

    private final String displayName;
    private final String department;
    private final String description;

    private ServiceGroupInfo(String displayName, String department, String description) {
        this.displayName = displayName;
        this.department = department;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDepartment() {
        return department;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ServiceGroupInfo{" +
                "setDisplayName='" + displayName + '\'' +
                ", setDepartment='" + department + '\'' +
                ", setDescription='" + description + '\'' +
                '}';
    }
}
