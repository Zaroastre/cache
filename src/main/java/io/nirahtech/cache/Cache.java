package io.nirahtech.cache;

import java.io.Closeable;
import java.util.Optional;

public sealed interface Cache<T> extends Closeable permits AbstractCache {
    Optional<T> get(final Key key);
    Key put(final T value);
    void put(final Key key, final T value);
    void delete(Key key);
    int size();
}
