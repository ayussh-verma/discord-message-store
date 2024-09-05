package com.ayusshverma.discord_message_store.user;

import java.util.List;
import java.util.stream.Collectors;

import com.ayusshverma.discord_message_store.dto.UserDto;
import com.ayusshverma.discord_message_store.dto.UserFieldsDto;
import com.ayusshverma.discord_message_store.exceptions.UserConflictException;
import com.ayusshverma.discord_message_store.exceptions.UserNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto createUser(String id, UserFieldsDto userFieldsDto) {
        UserEntity userEntity = userMapper.toUserEntity(id, userFieldsDto);

        if (userRepo.existsById(userEntity.getId())) {
            throw UserConflictException.fromUserId(id);
        }
        userRepo.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(String userId) {
        return userRepo.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> UserNotFoundException.fromUserId(userId));
    }

    @Transactional
    public UserDto updateUser(String id, UserFieldsDto userFieldsDto) {
        UserEntity userEntity = userMapper.toUserEntity(id, userFieldsDto);
        if (!userRepo.existsById(userEntity.getId())) {
            throw UserNotFoundException.fromUserId(id);
        }
        userRepo.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto deleteUser(String userId) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> UserNotFoundException.fromUserId(userId));
        userRepo.delete(userEntity);
        return userMapper.toUserDto(userEntity);
    }
}
