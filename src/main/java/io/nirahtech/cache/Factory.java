package io.nirahtech.cache;

import io.nirahtech.cache.configuration.Configuration;

public interface Factory<T> {
    T create(final Configuration configuration);
    T create();
}
