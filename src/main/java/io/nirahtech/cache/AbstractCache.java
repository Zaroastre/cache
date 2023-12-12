package io.nirahtech.cache;

import io.nirahtech.cache.configuration.Configuration;

abstract class AbstractCache<T> implements Cache<T> {

    protected final Configuration configuration;

    protected AbstractCache(final Configuration configuration) {
        this.configuration = configuration;
    }
}
