package com.ayusshverma.discord_message_store.exceptions;

public class UserExistsException extends RuntimeException {
    private static final String message = "User already exists";

    public UserExistsException() {
        super(message);
    }
}
