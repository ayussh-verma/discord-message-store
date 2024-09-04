package com.ayusshverma.discord_message_store.user;


import com.ayusshverma.discord_message_store.api.UsersApi;
import com.ayusshverma.discord_message_store.dto.UserDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class UserRestController implements UsersApi {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDto> getUser(String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
