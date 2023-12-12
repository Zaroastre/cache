package io.nirahtech.cache;

import java.util.UUID;

public final class Key {
    private final UUID id;

    public Key(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
