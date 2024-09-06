package com.ayusshverma.discord_message_store.user;

import java.time.OffsetDateTime;
import java.util.stream.Stream;

import com.ayusshverma.discord_message_store.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


@JsonTest
class UserDtoJsonTest {
    @Autowired
    private JacksonTester<UserDto> jacksonTester;

    private static UserDto initializeUserDto(
        String name,
        String avatarHash,
        String guildAvatarHash,
        OffsetDateTime joinedAt,
        OffsetDateTime createdAt,
        Boolean bot,
        Boolean inGuild,
        String id
    ) {
        UserDto userDto = new UserDto();
        userDto.setName(name);
        userDto.setAvatarHash(avatarHash);
        userDto.setGuildAvatarHash(guildAvatarHash);
        userDto.setJoinedAt(joinedAt);
        userDto.setCreatedAt(createdAt);
        userDto.setBot(bot);
        userDto.setInGuild(inGuild);
        userDto.setId(id);
        return userDto;
    }

    private static Stream<Arguments> provideUserDtosAndJsonPaths() {
        return Stream.of(
            Arguments.of(
                initializeUserDto(
                    "Foo",
                    "avatarHash",
                    "guildAvatarHash",
                    null,
                    OffsetDateTime.parse("2024-09-04T08:19:46.055205025Z"),
                    true,
                    true,
                    "2349234203942304929"
                ),
                "json/UserDto01.json"
            )
        );
    };

    @ParameterizedTest
    @MethodSource("provideUserDtosAndJsonPaths")
    void testSerialize(UserDto userDto, String jsonPath) throws Exception {
        assertThat(this.jacksonTester.write(userDto)).isEqualToJson(jsonPath);
    }

    @ParameterizedTest
    @MethodSource("provideUserDtosAndJsonPaths")
    void testDeserialize(UserDto userDto, String jsonPath) throws Exception {
        assertThat(this.jacksonTester.read(jsonPath)).isEqualTo(userDto);
    }
}