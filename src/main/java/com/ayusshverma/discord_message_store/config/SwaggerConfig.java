
package com.ayusshverma.discord_message_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(swaggerInfo());
    }

    private Info swaggerInfo() {
        Info info = new Info();
        info.setTitle("Discord Object Store API Documentation");
        info.setVersion("v0");
        info.setLicense(swaggerLicense());
        info.setContact(swaggerContact());
        return info;
    }

    private Contact swaggerContact() {
        Contact contact = new Contact();
        contact.setName("Ayussh Verma");
        contact.setEmail("ayussh.verma@maersk.com");
        contact.setUrl("https://github.com/ayussh-verma/discord-message-store");
        return contact;
    }

    private License swaggerLicense() {
        License license = new License();
        license.setName("MIT License");
        license.setUrl("https://github.com/ayussh-verma/discord-message-store/blob/main/LICENSE");
        license.setExtensions(Collections.emptyMap());
        return license;
    }

}