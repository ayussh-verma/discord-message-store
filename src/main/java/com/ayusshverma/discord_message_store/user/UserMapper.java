package com.ayusshverma.discord_message_store.user;

import com.ayusshverma.discord_message_store.dto.UserDto;
import com.ayusshverma.discord_message_store.dto.UserFieldsDto;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toUserDto(UserEntity userEntity);

    UserEntity toUserEntity(UserDto userDto);

    UserEntity toUserEntity(UserFieldsDto userFieldsDto);
}
