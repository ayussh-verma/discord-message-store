package com.ayusshverma.discord_message_store.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.ayusshverma.discord_message_store.ContainerTest;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepoTest extends ContainerTest {
    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("Foo");
        userEntity.setAvatarHash("avatarHash");
        userEntity.setGuildAvatarHash("guildAvatarHash");
        userEntity.setCreatedAt(OffsetDateTime.parse("2024-09-04T08:19:46.055205025Z"));
        userEntity.setInGuild(true);
        userRepo.save(userEntity);
    }

    @Test
    void shouldDeleteExistingUserAndPersist() {
        int count = userRepo.deleteByIdReturningCount("1");
        assertThat(count).isEqualTo(1);

        count = userRepo.deleteByIdReturningCount("1");
        assertThat(count).isEqualTo(0);
    }

    @Test
    void shouldntDeleteInvalidUser() {
        int count = userRepo.deleteByIdReturningCount("2");
        assertThat(count).isEqualTo(0);
    }

    @Test
    void shouldntDeleteInvalidUserWithSqlInjection() {
        int count = userRepo.deleteByIdReturningCount("1; DROP TABLE users");
        assertThat(count).isEqualTo(0);

        count = userRepo.deleteByIdReturningCount("1");
        assertThat(count).isEqualTo(1);
    }
}
