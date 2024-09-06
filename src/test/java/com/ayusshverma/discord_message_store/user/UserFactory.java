package com.ayusshverma.discord_message_store.user;

import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.HashMap;

import com.ayusshverma.discord_message_store.dto.UserDto;

public class UserFactory {
    public static final String FILE_TEMPLATE = "json/UserDto0%s.json";

    private static UserDto newUserDto(
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

    public static HashMap<String, UserDto> getUserDtos() {
        HashMap<String, UserDto> data = new HashMap<>();
        data.put(
            "wellFormed",
            newUserDto(
                "Random Name",
                "3424908298329048f8sdf90sd8f90sdf90sfsdf",
                "20309183901238912jksdfksdfjkshf90234902842903",
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 4, 8, 19, 46), ZoneOffset.UTC),
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 4, 8, 19, 46), ZoneOffset.UTC),
                false,
                true,
                "2349234203942304929"
            )
        );
        data.put(
            "empty",
            newUserDto(
                null, null, null, null, null, null, null, null)
        );
        data.put(
            "missingId",
            newUserDto(
                "Excessively intentionally very long name",
                "3453534895903589035839058390539053390595",
                "x",
                OffsetDateTime.of(LocalDateTime.of(2024, 1, 1, 0, 0, 0), ZoneOffset.UTC),
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 30, 8, 19, 46), ZoneOffset.UTC),
                false,
                true,
                null
            )
        );
        data.put(
            "onlyNecessary",
            newUserDto(
                "spaces and question mark?",
                null,
                null,
                null,
                OffsetDateTime.of(LocalDateTime.of(2024, 2, 29, 10, 20, 0), ZoneOffset.UTC),
                null,
                null,
                "23492342039423049"
            )
        );
        data.put(
            "timeZones",
            newUserDto(
                "reallylongnamenospacing",
                "?????????!@$#$@#%@#(@$)",
                "```odd characters ``` 'quotes'",
                OffsetDateTime.of(LocalDateTime.of(2024, 12, 4, 8, 59, 46), ZoneOffset.ofHours(5)),
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 4, 8, 19, 59), ZoneOffset.ofHours(-5)),
                true,
                false,
                "2349234203942304929"
            )
        );
        return data;
    }
}
