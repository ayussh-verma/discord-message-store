package com.ayusshverma.discord_message_store.user;

import com.ayusshverma.discord_message_store.dto.UserDto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

public class UserTestFactory {
    public static final HashMap<String, UserTestData> DATA = new HashMap<>();

    private static UserDto newUserDto(String name, String avatarHash, String guildAvatarHash,
            OffsetDateTime joinedAt, OffsetDateTime createdAt, Boolean bot, Boolean inGuild, String id) {
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

    public record UserTestData(UserDto userDto, String json) {
    }

    static {
        DATA.put("wellFormed", new UserTestData(newUserDto(
                "Random Name",
                "3424908298329048f8sdf90sd8f90sdf90sfsdf",
                "20309183901238912jksdfksdfjkshf90234902842903",
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 4, 8, 19, 46), ZoneOffset.UTC),
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 4, 8, 19, 46), ZoneOffset.UTC),
                false,
                true,
                "2349234203942304929"), """
                        {
                          "id": "2349234203942304929",
                          "name": "Random Name",
                          "avatarHash": "3424908298329048f8sdf90sd8f90sdf90sfsdf",
                          "guildAvatarHash": "20309183901238912jksdfksdfjkshf90234902842903",
                          "joinedAt": "2024-09-04T08:19:46.000Z",
                          "createdAt": "2024-09-04T08:19:46.000Z",
                          "bot": false,
                          "inGuild": true
                        }
                        """));
        DATA.put("empty", new UserTestData(newUserDto(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null), """
                        {
                          "id": null,
                          "name": null,
                          "avatarHash": null,
                          "guildAvatarHash": null,
                          "joinedAt": null,
                          "createdAt": null,
                          "bot": null,
                          "inGuild": null
                        }
                        """));
        DATA.put("missingId", new UserTestData(newUserDto(
                "Excessively intentionally very long name",
                "3453534895903589035839058390539053390595",
                "x",
                OffsetDateTime.of(LocalDateTime.of(2024, 1, 1, 0, 0, 0), ZoneOffset.UTC),
                OffsetDateTime.of(LocalDateTime.of(2024, 9, 30, 8, 19, 46), ZoneOffset.UTC),
                false,
                true,
                null), """
                        {
                          "id": null,
                          "name": "Excessively intentionally very long name",
                          "avatarHash": "3453534895903589035839058390539053390595",
                          "guildAvatarHash": "x",
                          "joinedAt": "2024-01-01T00:00:00.000Z",
                          "createdAt": "2024-09-30T08:19:46.000Z",
                          "bot": false,
                          "inGuild": true
                        }
                        """));
        DATA.put("onlyNecessary", new UserTestData(newUserDto(
                "spaces and question mark?",
                null,
                null,
                null,
                OffsetDateTime.of(LocalDateTime.of(2024, 2, 29, 10, 20, 0), ZoneOffset.UTC),
                null,
                null,
                "23492342039423049"), """
                        {
                          "id": "23492342039423049",
                          "name": "spaces and question mark?",
                          "avatarHash": null,
                          "guildAvatarHash": null,
                          "joinedAt": null,
                          "createdAt": "2024-02-29T10:20:00.000Z",
                          "bot": null,
                          "inGuild": null
                        }
                        """));
        // TODO: Need to improve the factory design, so that it can handle the following and other cases 
        // DATA.put("timeZones", new UserTestData(newUserDto(
        //         "reallylongnamenospacing",
        //         "?????????!@$#$@#%@#(@$)",
        //         "?????????!@$#$@#%@#(@$)",
        //         OffsetDateTime.of(LocalDateTime.of(2024, 2, 29, 10, 20, 0), ZoneOffset.ofHours(-5)),
        //         OffsetDateTime.of(LocalDateTime.of(2024, 2, 29, 10, 20, 0), ZoneOffset.ofHours(+5)),
        //         true,
        //         false,
        //         "23492342039423049"), """
        //                 {
        //                   "id": "23492342039423049",
        //                   "name": "reallylongnamenospacing",
        //                   "avatarHash": "?????????!@$#$@#%@#(@$)",
        //                   "guildAvatarHash": "?????????!@$#$@#%@#(@$)",
        //                   "joinedAt": "2024-02-29T15:20:00.000Z",
        //                   "createdAt": "2024-02-29T05:20:00.000Z",
        //                   "bot": true,
        //                   "inGuild": false
        //                 }
        //                 """));
    }
}