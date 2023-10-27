package io.nirahtech.cache;

import io.nirahtech.cache.configuration.Configuration;

final class InMemoryCacheFactory<T> implements Factory<Cache<T>> {
    @Override
    public Cache<T> create(final Configuration configuration) {
        return new InMemoryCache<>(configuration);
    }

    @Override
    public Cache<T> create() {
        final Configuration configuration = Configuration.builder().build();
        return new InMemoryCache<>(configuration);
    }
    
}
