package com.ayusshverma.discord_message_store;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ayusshverma.discord_message_store.models.UserDto;
import com.ayusshverma.discord_message_store.models.UserMapper;
// jackson
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ayusshverma.discord_message_store.models.User;

@SpringBootApplication
public class DiscordMessageStoreApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		// Assuming you want to use the current time for joinedAt and createdAt
		OffsetDateTime now = OffsetDateTime.now();

		User user = new User("123", "ayussh", "avatar", "guildAvatar", now, now, false, false, false, false, null);		// test serialization
		System.out.println(user);
		// use jackson
		String json = objectMapper.writeValueAsString(user);
		System.out.println(json);

		// test usermapper
		UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);
		System.out.println(userDto);
	}

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(DiscordMessageStoreApplication.class, args);
	}

}
