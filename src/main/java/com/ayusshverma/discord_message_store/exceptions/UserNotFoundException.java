package com.ayusshverma.discord_message_store.exceptions;

import com.ayusshverma.discord_message_store.exceptions.base.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    private static final String DEFAULT_MESSAGE_TEMPLATE = "A user having the ID '%s' does not exist.";

    public UserNotFoundException(String message) {
        super(message);
    }

    public static UserConflictException fromUserId(String userId) {
        return new UserConflictException(String.format(DEFAULT_MESSAGE_TEMPLATE, userId));
    }

}
