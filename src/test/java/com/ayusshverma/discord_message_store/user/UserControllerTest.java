package com.ayusshverma.discord_message_store.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.hamcrest.Matchers;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.util.HashMap;

import com.ayusshverma.discord_message_store.dto.UserDto;
import org.mockito.InjectMocks;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserRestController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserRestController userController;

    private static HashMap<String, UserDto> users;
    private UserDto userDto;

    @BeforeAll
    static void loadAllDtos() {
        users = UserFactory.getUserDtos();
    }

    @BeforeEach
    void init() throws IOException {
        userDto = users.get("wellFormed");
    }

    @Test
    void shouldGetUser_whenUserExists() throws Exception {
        Mockito.when(userService.getUser(userDto.getId())).thenReturn(userDto);
 
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", userDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(userDto.getId())))
                // TODO: Content-Length seems to be null
                // .andExpect(MockMvcResultMatchers.header().string("Content-Length", ))
                .andReturn();
    }
}
