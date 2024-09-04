package com.ayusshverma.discord_message_store.user;

import java.net.URI;
import java.util.List;

import com.ayusshverma.discord_message_store.api.UsersApi;
import com.ayusshverma.discord_message_store.dto.UserDto;
import com.ayusshverma.discord_message_store.dto.UserFieldsDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/")
class UserRestController implements UsersApi {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserDto> getUser(String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<UserDto> createUser(String userId, UserFieldsDto userFieldsDto) {
        UserDto userDto = userService.createUser(userId, userFieldsDto);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        URI location = uriComponentsBuilder.path("users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(location).body(userDto);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(String userId, UserFieldsDto userFieldsDto) {
        UserDto userDto = userService.updateUser(userId, userFieldsDto);
        return ResponseEntity.ok(userDto);
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
