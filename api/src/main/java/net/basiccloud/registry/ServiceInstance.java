package net.basiccloud.registry;

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.*;

/**
 * Service instance.
 */
public class ServiceInstance {

    public static class Builder {
        private String group;
        private String service;
        private String ip;
        private int port;
        private ServiceInstanceData data;
        private ServiceInstanceStatus status;

        private Builder() {

        }

        public Builder setGroup(String group) {
            checkArgument(!Strings.isNullOrEmpty(group), "group should not be null or empty");
            this.group = group;
            return this;
        }

        public Builder setService(String service) {
            checkArgument(!Strings.isNullOrEmpty(service), "service should not be null or empty");
            this.service = service;
            return this;
        }

        public Builder setIp(String ip) {
            checkArgument(!Strings.isNullOrEmpty(ip), "ip should not be null or empty");
            this.ip = ip;
            return this;
        }

        public Builder setPort(int port) {
            checkArgument(port > 0, "port should be positive: port=" + port);
            checkArgument(port < 65535, "port should be less than 65535: port=" + port);
            this.port = port;
            return this;
        }

        public Builder setData(ServiceInstanceData data) {
            this.data = data;
            return this;
        }

        public Builder setStatus(ServiceInstanceStatus status) {
            this.status = status;
            return this;
        }

        public ServiceInstance build() {
            checkState(group != null, "please set group before build()");
            checkState(service != null, "please set service before build()");
            checkState(ip != null, "please set ip before build()");
            checkState(port > 0, "please set port before build()");
            return new ServiceInstance(group, service, ip, port, data, status);
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
     * create a new builder with data in given instance.
     * @param instance the instance to be added to the new builder
     * @return a new builder with data in given instance
     */
    public static Builder newBuilder(ServiceInstance instance) {
        Builder builder = new Builder();
        builder.setGroup(instance.getGroup())
                .setService(instance.getService())
                .setIp(instance.getIp())
                .setPort(instance.getPort())
                .setData(instance.getData())
                .setStatus(instance.getStatus());
        return builder;
    }

    private final String id;
    private final String group;
    private final String service;
    private final String ip;
    private final int port;
    private final ServiceInstanceData data;
    private final ServiceInstanceStatus status;

    private ServiceInstance(String group, String service, String ip, int port,
                            ServiceInstanceData data, ServiceInstanceStatus status) {
        this.group = group;
        this.service = service;
        this.ip = ip;
        this.port = port;
        this.id = group + "-" + service + "-" + ip + "-" + port;
        this.data = data;
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public String getService() {
        return service;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getId() {
        return id;
    }

    public ServiceInstanceData getData() {
        return data;
    }

    public ServiceInstanceStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceInstance that = (ServiceInstance) o;

        if (port != that.port) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "group='" + group + '\'' +
                ", service='" + service + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", data=" + data +
                ", status=" + status +
                '}';
    }
}
