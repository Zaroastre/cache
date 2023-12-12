package io.nirahtech.cache;

import java.io.Closeable;
import java.util.Optional;

public interface Cache<T> extends Closeable  {
    Optional<T> get(final Key key);
    Key put(final T value);
    void put(final Key key, final T value);
    void delete(Key key);
    int size();
}
