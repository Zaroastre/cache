package io.nirahtech.cache;

import java.time.Duration;
import java.time.LocalDateTime;

public final class CachedData<K, V> implements Refresh {
    private final K key;
    private LocalDateTime from;
    private V data;
    private LocalDateTime expirationDate;
    private final Duration rententionDuration;
    
    public CachedData(final K key, final V data, final Duration rententionDuration) {
        this.key = key;
        this.from = LocalDateTime.now();
        this.data = data;
        this.rententionDuration = rententionDuration;
        this.expirationDate = this.from.plus(rententionDuration);
    }

    public K getKey() {
        return this.key;
    }
    public V getData() {
        return this.data;
    }
    public LocalDateTime getFrom() {
        return this.from;
    }
    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public void refresh() {
        this.expirationDate = LocalDateTime.now().plus(rententionDuration);
    }
}
