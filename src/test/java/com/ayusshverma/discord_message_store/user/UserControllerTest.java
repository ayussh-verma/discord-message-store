package com.ayusshverma.discord_message_store.user;

import com.ayusshverma.discord_message_store.dto.UserDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

    @Test
    void shouldGetUser_whenUserExists() throws Exception {
        UserDto userDto = UserTestFactory.DATA.get("wellFormed").userDto();

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
