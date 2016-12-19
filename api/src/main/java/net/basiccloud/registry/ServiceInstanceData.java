package net.basiccloud.registry;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.collect.Collections2;

import java.util.*;

import static com.google.common.base.Preconditions.*;

/**
 * Data of setService instance.
 */
public class ServiceInstanceData {

    /**
     * constant of dolphin setService type, "DOLPHIN"
     */
    public static final String DOLPHIN_SERVICE_TYPE = "DOLPHIN";

    /**
     * constant of rest setService type, "REST"
     */
    public static final String REST_SERVICE_TYPE = "REST";

    /**
     * setPort protocol of gRPC, "grpc"
     */
    public static final String PROTOCOL_GRPC = "grpc";

    /**
     * setPort protocol of gRPC SSL, "grpcs"
     */
    public static final String PROTOCOL_GRPCS = "grpcs";

    /**
     * setPort protocol of HTTP, "http"
     */
    public static final String PROTOCOL_HTTP = "http";

    /**
     * setPort protocol of HTTPS, "https"
     */
    public static final String PROTOCOL_HTTPS = "https";

    public static class Builder {
        private String serviceType = DOLPHIN_SERVICE_TYPE;
        private Version serviceVersion;
        private Version frameworkVersion;
        private WorkMode workMode = WorkMode.NORMAL;
        private Set<String> tags = new HashSet<>();
        private Map<String, Integer> ports = new HashMap<>();

        private Builder() {

        }

        public Builder setServiceType(String serviceType) {
            this.serviceType = Strings.emptyToNull(serviceType);
            return this;
        }

        public Builder setServiceVersion(Version serviceVersion) {
            checkNotNull(serviceVersion, "serviceVersion should not be null.");
            this.serviceVersion = serviceVersion;
            return this;
        }

        public Builder setServiceVersion(String serviceVersion) {
            checkNotNull(serviceVersion, "serviceVersion should not be null.");
            this.serviceVersion = Version.valueOf(serviceVersion);
            return this;
        }

        public Builder setFrameworkVersion(Version frameworkVersion) {
            checkNotNull(frameworkVersion, "frameworkVersion should not be null.");
            this.frameworkVersion = frameworkVersion;
            return this;
        }

        public Builder setFrameworkVersion(String frameworkVersion) {
            checkNotNull(frameworkVersion, "frameworkVersion should not be null.");
            this.frameworkVersion = Version.valueOf(frameworkVersion);
            return this;
        }

        public Builder setWorkMode(WorkMode workMode) {
            checkNotNull(workMode, "workMode should not be null.");
            this.workMode = workMode;
            return this;
        }

        /**
         * set tag list. <p> <p>all exist tags will be clean，even given tags is null.
         *
         * @param tags tag list
         * @return this builder
         */
        public Builder setTags(String... tags) {
            this.tags.clear();
            this.addTag(tags);
            return this;
        }

        /**
         * set tag list. <p> <p>all exist tags will be clean，even given tags is null.
         *
         * @param tagList tag list
         * @return this builder
         */
        public Builder setTags(Collection<String> tagList) {
            this.tags.clear();
            this.addTag(tagList);
            return this;
        }

        public Builder addTag(String... tags) {
            if (tags != null) {
                for (String tag : tags) {
                    if (!Strings.isNullOrEmpty(tag)) {
                        this.tags.add(tag.trim());
                    }
                }
            }
            return this;
        }

        public Builder addTag(Collection<String> tagList) {
            if (tagList != null) {
                tagList.forEach(this::addTag);
            }
            return this;
        }

        public Builder setPorts(Map<String, Integer> ports) {
            this.ports.clear();
            this.addPort(ports);
            return this;
        }

        public Builder addPort(String protocol, int port) {
            checkArgument(!Strings.isNullOrEmpty(protocol), "protocol should not be null or empty.");
            checkArgument(port > 0, "setPort should be positive.");
            checkArgument(port < 65535, "setPort should between 0 and 65535.");
            this.ports.put(protocol.trim(), port);

            return this;
        }

        public Builder addPort(Map<String, Integer> ports) {
            if (ports != null && ports.size() > 0) {
                Iterator<String> iterator = ports.keySet().iterator();
                while (iterator.hasNext()) {
                    String protocol = iterator.next();
                    Integer port = ports.get(protocol);
                    if (port != null) {
                        this.addPort(protocol, port);
                    }
                }
            }

            return this;
        }

        public Builder addGrpcPort(int port) {
            addPort(PROTOCOL_GRPC, port);
            return this;
        }

        public Builder addGrpcsPort(int port) {
            addPort(PROTOCOL_GRPCS, port);
            return this;
        }

        public Builder addHttpPort(int port) {
            addPort(PROTOCOL_HTTP, port);
            return this;
        }

        public Builder addHttpPorts(int port) {
            addPort(PROTOCOL_HTTPS, port);
            return this;
        }

        public ServiceInstanceData build() {
            checkState(serviceVersion != null, "please set serviceVersion before build()");
            checkState(frameworkVersion != null, "please set frameworkVersion before build()");


            return new ServiceInstanceData(serviceType, serviceVersion, frameworkVersion, workMode,
                    tags.toArray(new String[tags.size()]), Collections.unmodifiableMap(ports));
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
     * create a new builder with data.
     *
     * @param data the data to be added to the new builder
     * @return a new builder with given data
     */
    public static Builder newBuilder(ServiceInstanceData data) {
        Builder builder = new Builder();
        builder.setServiceType(data.getServiceType())
                .setServiceVersion(data.getServiceVersion())
                .setFrameworkVersion(data.getFrameworkVersion())
                .setTags(data.getTags())
                .setPorts(data.getPorts());
        return builder;
    }

    private final String serviceType;
    private final Version serviceVersion;
    private final Version frameworkVersion;
    private final WorkMode workMode;
    private final String[] tags;
    private final Map<String, Integer> ports;

    private ServiceInstanceData(String serviceType, Version serviceVersion, Version frameworkVersion, WorkMode workMode,
                                String[] tags, Map<String, Integer> ports) {
        this.serviceType = serviceType;
        this.serviceVersion = serviceVersion;
        this.frameworkVersion = frameworkVersion;
        this.workMode = workMode;
        this.tags = tags;
        this.ports = ports;
    }

    public String getServiceType() {
        return serviceType;
    }

    public boolean isDolphinService() {
        return DOLPHIN_SERVICE_TYPE.equals(this.serviceType);
    }

    public boolean isRestService() {
        return REST_SERVICE_TYPE.equals(this.serviceType);
    }

    public Version getServiceVersion() {
        return serviceVersion;
    }

    public Version getFrameworkVersion() {
        return frameworkVersion;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public String[] getTags() {
        return tags;
    }

    public Map<String, Integer> getPorts() {
        return ports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInstanceData that = (ServiceInstanceData) o;
        return com.google.common.base.Objects.equal(serviceType, that.serviceType) &&
                Objects.equal(serviceVersion, that.serviceVersion) &&
                Objects.equal(frameworkVersion, that.frameworkVersion) &&
                Objects.equal(workMode, that.workMode) &&
                Arrays.equals(tags, that.tags) &&
                Objects.equal(ports, that.ports);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(serviceType, serviceVersion, frameworkVersion, workMode, tags, ports);
    }

    @Override
    public String toString() {
        return "ServiceInstanceData{" +
                "serviceType='" + serviceType + '\'' +
                ", serviceVersion=" + serviceVersion +
                ", frameworkVersion=" + frameworkVersion +
                ", workMode=" + workMode +
                ", tags=" + Arrays.toString(tags) +
                ", ports=" + ports +
                '}';
    }
}
