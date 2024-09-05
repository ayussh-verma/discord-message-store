package com.ayusshverma.discord_message_store.exceptions.base;

public abstract class EntityConflictException extends RuntimeException {
    public EntityConflictException(String message) {
        super(message);
    }
}
