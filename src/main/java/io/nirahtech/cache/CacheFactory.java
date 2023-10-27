package io.nirahtech.cache;

import io.nirahtech.cache.configuration.Configuration;

public final class CacheFactory {
    private CacheFactory() { }

    
    public static final <T> Cache<T> createInMemoryCache() {
        return new InMemoryCacheFactory<T>().create();
    }
    
    public static final <T> Cache<T> createInMemoryCache(final Configuration configuration) {
        return new InMemoryCacheFactory<T>().create(configuration);
    }

}
