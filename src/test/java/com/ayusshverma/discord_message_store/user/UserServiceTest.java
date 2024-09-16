package com.ayusshverma.discord_message_store.user;

import com.ayusshverma.discord_message_store.dto.UserDto;
import com.ayusshverma.discord_message_store.dto.UserFieldsDto;
import com.ayusshverma.discord_message_store.exceptions.UserConflictException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private UserFieldsDto userFieldsDto;
    private UserEntity userEntity;

    @BeforeEach
    void init() {
        userDto = UserTestFactory.DATA.get("wellFormed").userDto();
        userFieldsDto = userMapper.toUserFieldsDto(userDto);
        userEntity = userMapper.toUserEntity(userDto);
        userService = new UserService(userRepo, userMapper);
    }

    @Test
    void shouldCreateUser_whenUserDoesNotExist() {
        Mockito.when(userRepo.existsById(userEntity.getId())).thenReturn(false);
        Mockito.when(userRepo.save(userEntity)).thenReturn(userEntity);

        UserDto createdUser = userService.createUser(userDto.getId(), userFieldsDto);

        Assertions.assertEquals(userDto, createdUser);

        Mockito.verify(userRepo).existsById(userEntity.getId());
        Mockito.verify(userRepo).save(userEntity);

        Mockito.verifyNoMoreInteractions(userRepo);
    }

    @Test
    void shouldntCreateUser_whenUserExists() {
        Mockito.when(userRepo.existsById(userEntity.getId())).thenReturn(true);

        Assertions.assertThrows(UserConflictException.class, () -> {
            userService.createUser(userDto.getId(), userFieldsDto);
        });

        Mockito.verify(userRepo).existsById(userEntity.getId());
        Mockito.verifyNoMoreInteractions(userRepo);
    }

    @Test
    void shouldGetUser_whenUserExists() {
        Mockito.when(userRepo.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));

        UserDto retrievedUser = userService.getUser(userDto.getId());

        Assertions.assertEquals(userDto, retrievedUser);

        Mockito.verify(userRepo).findById(userEntity.getId());
        Mockito.verifyNoMoreInteractions(userRepo);
    }
}
