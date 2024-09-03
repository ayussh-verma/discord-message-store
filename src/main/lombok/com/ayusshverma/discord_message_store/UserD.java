package com.ayusshverma.discord_message_store;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserD {
    @Id
    @Column(length = 64)
    @Size(min = 1, max = 64)
    private String id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(name = "avatar_hash", nullable = true)
    private String avatarHash;

    @Column(name = "guild_avatar_hash", nullable = true)
    private String guildAvatarHash;

    @Column(name = "joined_at", nullable = false)
    private OffsetDateTime joinedAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "is_staff", nullable = false)
    private boolean isStaff;

    @Column(nullable = false)
    private boolean bot = false;

    @Column(name = "in_guild", nullable = false)
    private boolean inGuild = false;

    @Column(nullable = false)
    private boolean pending = false;
}