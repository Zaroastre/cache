package io.nirahtech.cache.configuration;

import java.time.Duration;

public final class Configuration {

    private final Duration retentionDuration;
    private final int maxSize;
    private final Duration cleanupInterval;

    public Configuration(final Duration retentionDuration, final int maxSize, final Duration cleanupInterval) {
        this.retentionDuration = retentionDuration;
        this.maxSize = maxSize;
        this.cleanupInterval = cleanupInterval;
    }

    public Duration getRetentionDuration() {
        return retentionDuration;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public Duration getCleanupInterval() {
        return cleanupInterval;
    }

    public static final ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }
}
