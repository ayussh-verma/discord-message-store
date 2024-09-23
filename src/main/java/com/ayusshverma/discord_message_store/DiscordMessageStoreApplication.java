package com.ayusshverma.discord_message_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan 
public class DiscordMessageStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscordMessageStoreApplication.class, args);
    }

}
