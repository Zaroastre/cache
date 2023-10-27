package io.nirahtech.cache.configuration;

import java.time.Duration;

import io.nirahtech.cache.Builder;

public final class ConfigurationBuilder implements Builder<Configuration> {

    
    private Duration retentionDuration;
    private int maxSize;
    private Duration cleanupInterval;

    public ConfigurationBuilder() { 
        this.retentionDuration = Duration.ofHours(1);
        this.maxSize = 1_000;
        this.cleanupInterval = Duration.ofMinutes(1);
    }

    public ConfigurationBuilder retentionDuration(final Duration retentionDuration) {
        this.retentionDuration = retentionDuration;
        return this;
    }
    public ConfigurationBuilder maxSize(final int maxSize) {
        this.maxSize = maxSize;
        return this;
    }
    public ConfigurationBuilder cleanupInterval(final Duration cleanupInterval) {
        this.cleanupInterval = cleanupInterval;
        return this;
    }

    @Override
    public Configuration build() {
        return new Configuration(this.retentionDuration, maxSize, cleanupInterval);
    }
    
}
