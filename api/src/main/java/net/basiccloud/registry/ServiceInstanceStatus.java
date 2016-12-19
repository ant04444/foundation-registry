package net.basiccloud.registry;

import static com.google.common.base.Preconditions.*;
import static net.basiccloud.registry.ServiceInstanceStatus.Status.*;

/**
 * Status of setService instance.
 */
public class ServiceInstanceStatus {

    public enum Status {
        /**
         * online.
         */
        ONLINE,

        /**
         * offline.
         */
        OFFLINE
    }

    public static class Builder {
        private Status status;
        private long lastUpdateTime;
        private String comment;
        private long leaseId;

        private Builder() {

        }

        public Builder setStatus(Status status) {
            checkNotNull(status, "status should not be null.");
            this.status = status;
            return this;
        }

        public Builder setLastUpdateTime(long lastUpdateTime) {
            checkArgument(lastUpdateTime > 0, "lastUpdateTime should be positive.");
            this.lastUpdateTime = lastUpdateTime;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setLeaseId(long leaseId) {
            checkArgument(leaseId >= 0, "leaseId should not be negative.");
            this.leaseId = leaseId;
            return this;
        }

        public Builder asOnline() {
            this.status = ONLINE;
            this.lastUpdateTime = System.currentTimeMillis();
            this.comment = "Service is online.";
            return this;
        }

        public Builder asOffline() {
            this.status = Status.OFFLINE;
            this.lastUpdateTime = System.currentTimeMillis();
            this.comment = "Service is offline.";
            return this;
        }

        public ServiceInstanceStatus build() {
            checkState(status != null, "status should be set before build");
            checkState(lastUpdateTime > 0, "lastUpdateTime should be set before build");

            return new ServiceInstanceStatus(status, lastUpdateTime, comment, leaseId);
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
     * create a new builder with status.
     * @param status the status to be added to the new builder
     * @return a new builder with given status
     */
    public static Builder newBuilder(ServiceInstanceStatus status) {
        Builder builder = new Builder();
        builder.setStatus(status.getStatus())
                .setLastUpdateTime(status.getLastUpdateTime())
                .setComment(status.getComment())
                .setLeaseId(status.getLeaseId());
        return builder;
    }


    private final Status status;
    private final long lastUpdateTime;
    private final String comment;
    private final long leaseId;

    private ServiceInstanceStatus(Status status, long lastUpdateTime, String comment, long leaseId) {
        this.status = status;
        this.lastUpdateTime = lastUpdateTime;
        this.comment = comment;
        this.leaseId = leaseId;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isOnline() {
        return this.status.equals(ONLINE);
    }

    public boolean isOffline() {
        return this.status.equals(OFFLINE);
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getComment() {
        return comment;
    }

    public long getLeaseId() {
        return leaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceInstanceStatus that = (ServiceInstanceStatus) o;

        return lastUpdateTime == that.lastUpdateTime && leaseId == that.leaseId && status == that.status && (comment != null ? comment.equals(
                that.comment) : that.comment == null);
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (int) (leaseId ^ (leaseId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ServiceInstanceStatus{" +
                "status=" + status +
                ", lastUpdateTime=" + lastUpdateTime +
                ", comment='" + comment + '\'' +
                ", leaseId=" + leaseId +
                '}';
    }
}
