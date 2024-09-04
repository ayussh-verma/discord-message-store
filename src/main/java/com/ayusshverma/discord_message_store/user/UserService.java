package com.ayusshverma.discord_message_store.user;

import java.util.List;
import java.util.stream.Collectors;

import com.ayusshverma.discord_message_store.dto.UserDto;
import com.ayusshverma.discord_message_store.exceptions.UserExistsException;
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
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        if (userRepo.existsById(userEntity.getId())) {
            throw new UserExistsException();
        }
        userRepo.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(String userId) {
        return userRepo.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional
    public UserDto editUser(UserDto userFieldsDto) {
        UserEntity userEntity = userMapper.toUserEntity(userFieldsDto);
        if (!userRepo.existsById(userEntity.getId())) {
            throw new UserNotFoundException();
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
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        if (!userRepo.existsById(userEntity.getId())) {
            throw new UserNotFoundException();
        }
        userRepo.save(userEntity);
        return userMapper.toUserDto(userEntity);
    }

    @Transactional
    public UserDto deleteUser(String userId) {
        UserEntity userEntity = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());
        userRepo.delete(userEntity);
        return userMapper.toUserDto(userEntity);
    }
}
