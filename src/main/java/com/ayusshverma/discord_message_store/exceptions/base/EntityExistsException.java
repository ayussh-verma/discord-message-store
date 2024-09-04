package com.ayusshverma.discord_message_store.exceptions.base;

public class EntityExistsException extends RuntimeException {
    private static final String message = "Entity already exists";

    EntityExistsException() {
        super(message);
    }
}
