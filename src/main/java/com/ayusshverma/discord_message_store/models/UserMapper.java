package com.ayusshverma.discord_message_store.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

// handle user and userdto
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}