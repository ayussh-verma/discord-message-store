package com.ayusshverma.discord_message_store.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepoTest {
    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

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
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
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
}
