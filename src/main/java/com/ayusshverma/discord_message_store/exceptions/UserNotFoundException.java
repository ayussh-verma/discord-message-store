package com.ayusshverma.discord_message_store.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final String message = "User not found";

    public UserNotFoundException() {
        super(message);
    }
}
