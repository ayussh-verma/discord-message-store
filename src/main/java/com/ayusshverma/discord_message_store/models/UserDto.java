package com.ayusshverma.discord_message_store.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    @JsonProperty("id")
    public Long id;
    @JsonProperty("name")
    public String name;
}
