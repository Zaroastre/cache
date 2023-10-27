package io.nirahtech.cache;

import io.nirahtech.cache.configuration.Configuration;

public sealed interface Factory<T> permits InMemoryCacheFactory {
    T create(final Configuration configuration);
    T create();
}
