package com.ayusshverma.discord_message_store.exceptions.base;

public abstract class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
