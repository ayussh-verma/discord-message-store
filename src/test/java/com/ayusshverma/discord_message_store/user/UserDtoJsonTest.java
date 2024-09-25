package com.ayusshverma.discord_message_store.user;

import com.ayusshverma.discord_message_store.dto.UserDto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class UserDtoJsonTest {
    @Autowired
    private JacksonTester<UserDto> jacksonTester;

    private static Stream<Arguments> fromUserTestFactory() {
        return UserTestFactory.DATA.entrySet().stream()
                .map(entry -> Arguments.of(entry.getKey(), entry.getValue().userDto(), entry.getValue().json()));
    }

    @ParameterizedTest
    @MethodSource("fromUserTestFactory")
    void testSerialize(String testName, UserDto userDto, String jsonContent) throws Exception {
        assertThat(this.jacksonTester.write(userDto)).isEqualToJson(jsonContent);
    }

    @ParameterizedTest
    @MethodSource("fromUserTestFactory")
    void testDeserialize(String testName, UserDto userDto, String jsonContent) throws Exception {
        assertThat(this.jacksonTester.parse(jsonContent).getObject()).isEqualTo(userDto);
    }
}