package com.ayusshverma.discord_message_store.exceptions.base;

public class EntityNotFoundException extends RuntimeException {
    private static final String message = "Entity not found";

    public EntityNotFoundException() {
        super(message);
    }
}
