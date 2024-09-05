package com.ayusshverma.discord_message_store.exceptions;

import com.ayusshverma.discord_message_store.exceptions.base.EntityConflictException;

public class UserConflictException extends EntityConflictException {
    private static final String DEFAULT_MESSAGE_TEMPLATE = "A user having the ID '%s' already exists in the database.";

    public UserConflictException(String message) {
        super(message);
    }

    public static UserConflictException fromUserId(String userId) {
        return new UserConflictException(String.format(DEFAULT_MESSAGE_TEMPLATE, userId));
    }
}
