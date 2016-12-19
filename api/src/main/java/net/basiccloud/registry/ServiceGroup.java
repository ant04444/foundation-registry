package net.basiccloud.registry;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Service setGroup.
 */
public class ServiceGroup {

    public static class Builder {

        private String group;
        private ServiceGroupInfo info;
        private List<Service> serviceList = new ArrayList<>();

        private Builder() {

        }

        public Builder setGroup(String group) {
            checkArgument(!Strings.isNullOrEmpty(group), "setGroup should not be null or empty");
            this.group = group;
            return this;
        }

        public Builder setInfo(ServiceGroupInfo info) {
            this.info = info;
            return this;
        }

        /**
         * set setService list.
         * <p>
         * <p>all exist services will be clean，even given serviceList is null.
         *
         * @param serviceList setService list
         * @return this builder
         */
        public Builder setServiceList(List<Service> serviceList) {
            this.serviceList.clear();
            if (serviceList != null) {
                serviceList.forEach(this::addService);
            }
            return this;
        }

        public Builder addService(Service service) {
            if (service != null) {
                this.serviceList.add(service);
            }
            return this;
        }

        public Builder addService(Service... serviceList) {
            if (serviceList != null) {
                for (Service service : serviceList) {
                    addService(service);
                }
            }
            return this;
        }

        public Builder addService(Collection<Service> serviceList) {
            if (serviceList != null) {
                serviceList.forEach(this::addService);
            }
            return this;
        }

        public ServiceGroup build() {
            checkState(group != null, "please set setGroup before build()");
            checkGroupInService();

            return new ServiceGroup(group, info, Collections.unmodifiableList(serviceList));
        }

        private void checkGroupInService() {
            for (Service service : serviceList) {
                checkState(group.equals(service.getGroup()),
                        "setGroup of Service(%s) should equal to setGroup of ServiceGroup(%s)",
                        service.getGroup(), group);
            }
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
     * create a new builder with data in given serviceGroup.
     *
     * @param serviceGroup the serviceGroup to be added to the new builder
     * @return a new builder with data in given serviceGroup
     */
    public static Builder newBuilder(ServiceGroup serviceGroup) {
        return new Builder().setGroup(serviceGroup.getGroup())
                .setInfo(serviceGroup.getInfo())
                .setServiceList(serviceGroup.getServiceList());
    }


    private final String group;
    private final ServiceGroupInfo info;
    private final List<Service> serviceList;

    private ServiceGroup(String group, ServiceGroupInfo info, List<Service> serviceList) {
        this.group = group;
        this.info = info;
        this.serviceList = serviceList;
    }

    public String getGroup() {
        return group;
    }

    /**
     * get setGroup information(perhaps null).
     *
     * @return setGroup information, null if absent
     */
    public ServiceGroupInfo getInfo() {
        return info;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    @Override
    public String toString() {
        return "ServiceGroup{" +
                "setGroup='" + group + '\'' +
                ", setInfo=" + info +
                ", serviceList=" + serviceList +
                '}';
    }
}
