package net.basiccloud.registry;

/**
 * work mode of service instance.
 */
public enum WorkMode {
    /**
     * normal mode, available to the clients.
     */
    NORMAL(0, true),

    /**
     * maintenance mode, unavailable to the clients.
     */
    MAINTENANCE(1, false),

    /**
     * degrade mode, available to the clients but with low quality(degraded).
     */
    DEGRADE(2, true);

    private final int code;

    private final boolean available;

    WorkMode(int code, boolean available) {
        this.code = code;
        this.available = available;
    }

    public int getCode() {
        return code;
    }

    /**
     * Is available to the clients.
     *
     * @return true if available to the clients
     */
    public boolean isAvailable() {
        return available;
    }
}
