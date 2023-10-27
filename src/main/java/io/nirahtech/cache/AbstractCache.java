package io.nirahtech.cache;

import io.nirahtech.cache.configuration.Configuration;

abstract sealed class AbstractCache<T> implements Cache<T> permits InMemoryCache {

    protected final Configuration configuration;

    protected AbstractCache(final Configuration configuration) {
        this.configuration = configuration;
    }
}
