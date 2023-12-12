package io.nirahtech.cache;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.nirahtech.cache.configuration.Configuration;

public final class InMemoryCache<T> extends AbstractCache <T> {

    private final Map<Key, CachedData<Key, T>> datasource = new HashMap<>();
    private final ScheduledExecutorService scheduledExecutorService;

    public InMemoryCache(final Configuration configuration) {
        super(configuration);
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.scheduledExecutorService.scheduleAtFixedRate(() -> {
            synchronized (this.datasource) {
                final List<Key> expiredKeys = this.datasource.entrySet().stream().filter(entry -> entry.getValue().getExpirationDate().isBefore(LocalDateTime.now())).map(entry -> entry.getKey()).collect(Collectors.toList());
                expiredKeys.forEach(key -> {
                    this.delete(key);
                });
            }
        }, 0, this.configuration.getCleanupInterval().getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public synchronized Optional<T> get(Key key) {
        Optional<T> data = Optional.empty();
        if (Objects.nonNull(key)) {
            if (this.datasource.containsKey(key)) {
                final CachedData<Key, T> cachedData = this.datasource.get(key);
                data = Optional.ofNullable(cachedData.getData());
            }
        }
        return data;
    }

    @Override
    public synchronized Key put(T value) {
        Key key = null;
        if (Objects.nonNull(value)) {
            key = new Key(UUID.randomUUID());
            this.put(key, value);
        }
        return key;
    }

    @Override
    public synchronized void put(Key key, T value) {
        if (Objects.nonNull(key) && Objects.nonNull(value)) {
            final CachedData<Key, T> cachedData;
            if (this.datasource.containsKey(key)) {
                cachedData = this.datasource.get(key);
                cachedData.refresh();
            } else {
                cachedData = new CachedData<>(key, value, this.configuration.getRetentionDuration());
            }
            if (this.datasource.size() < this.configuration.getMaxSize()) {
                this.datasource.put(key, cachedData);
            }
        }
    }

    @Override
    public synchronized void delete(Key key) {
        if (Objects.nonNull(key)) {
            if (this.datasource.containsKey(key)) {
                this.datasource.remove(key);
            }
        }
    }

    @Override
    public int size() {
        return this.datasource.size();
    }

    @Override
    public void close() throws IOException {
        this.datasource.clear();
        this.scheduledExecutorService.shutdownNow();
        // this.scheduledExecutorService.close();
    }
}
