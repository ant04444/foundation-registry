package net.basiccloud.registry;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Service.
 */
public class Service {

    public static class Builder {

        private String group;
        private String service;
        private ServiceInfo info;
        private List<ServiceInstance> instanceList = new ArrayList<>();

        private Builder() {

        }

        public Builder setGroup(String group) {
            checkArgument(!Strings.isNullOrEmpty(group), "setGroup should not be null or empty");
            this.group = group;
            return this;
        }

        public Builder setService(String service) {
            checkArgument(!Strings.isNullOrEmpty(service), "setService should not be null or empty");
            this.service = service;
            return this;
        }

        public Builder setInfo(ServiceInfo info) {
            this.info = info;
            return this;
        }

        /**
         * set instance list.
         * <p>
         * <p>all exist instances will be clean，even given instanceList is null.
         *
         * @param instanceList instance list
         * @return this builder
         */
        public Builder setInstanceList(List<ServiceInstance> instanceList) {
            this.instanceList.clear();
            if (instanceList != null) {
                instanceList.forEach(this::addInstance);
            }
            return this;
        }

        public Builder addInstance(ServiceInstance instance) {
            if (instance != null) {
                this.instanceList.add(instance);
            }
            return this;
        }

        public Builder addInstance(ServiceInstance... instanceList) {
            if (instanceList != null) {
                for (ServiceInstance instance : instanceList) {
                    addInstance(instance);
                }
            }
            return this;
        }

        public Builder addInstance(Collection<ServiceInstance> instanceList) {
            if (instanceList != null) {
                instanceList.forEach(this::addInstance);
            }
            return this;
        }

        public Service build() {
            checkState(group != null, "please set setGroup before build()");
            checkState(service != null, "please set setService before build()");
            checkGroupAndServiceInInstance();

            return new Service(group, service, info, Collections.unmodifiableList(instanceList));
        }

        private void checkGroupAndServiceInInstance() {
            for (ServiceInstance instance : instanceList) {
                checkState(group.equals(instance.getGroup()),
                        "setGroup of Service(%s) should equal to setGroup of ServiceInstance(%s)",
                        group, instance.getGroup());
                checkState(service.equals(instance.getService()),
                        "setService of Service(%s) should equal to setService of ServiceInstance(%s)",
                        service, instance.getService());
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
     * create a new builder with data in given setService.
     *
     * @param service the setService to be added to the new builder
     * @return a new builder with data in given setService
     */
    public static Builder newBuilder(Service service) {
        return new Builder().setGroup(service.getGroup())
                .setService(service.getService())
                .setInfo(service.getInfo())
                .setInstanceList(service.getInstanceList());
    }

    private final String group;
    private final String service;
    private final ServiceInfo info;
    private final List<ServiceInstance> instanceList;

    private Service(String group, String service, ServiceInfo info, List<ServiceInstance> instanceList) {
        this.group = group;
        this.service = service;
        this.info = info;
        this.instanceList = instanceList;
    }

    public String getGroup() {
        return group;
    }

    public String getService() {
        return service;
    }

    /**
     * get setService information(perhaps null).
     *
     * @return setService information, null if absent
     */
    public ServiceInfo getInfo() {
        return info;
    }

    public List<ServiceInstance> getInstanceList() {
        return instanceList;
    }

    @Override
    public String toString() {
        return "Service{" +
                "setGroup='" + group + '\'' +
                ", setService='" + service + '\'' +
                ", setInfo=" + info +
                ", instanceList=" + instanceList +
                '}';
    }
}
