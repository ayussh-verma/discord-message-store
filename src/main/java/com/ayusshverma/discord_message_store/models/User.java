package com.ayusshverma.discord_message_store.models;

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
public class User {
    @Id
    @Column(length = 64)
    @Size(min = 1, max = 64)
    public String id;

    @Column(length = 255, nullable = false)
    public String name;

    @Column(name = "avatar_hash", nullable = true)
    public String avatarHash;

    @Column(name = "guild_avatar_hash", nullable = true)
    public String guildAvatarHash;

    @Column(name = "joined_at", nullable = false)
    public OffsetDateTime joinedAt;

    @Column(name = "created_at", nullable = false)
    public OffsetDateTime createdAt;

    @Column(name = "is_staff", nullable = false)
    public boolean isStaff;

    @Column(nullable = false)
    public boolean bot = false;

    @Column(name = "in_guild", nullable = false)
    public boolean inGuild = false;

    @Column(nullable = false)
    public boolean pending = false;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @JsonIgnore
    public List<Message> messages;

    public User(String id, String name, String avatarHash, String guildAvatarHash, OffsetDateTime joinedAt, OffsetDateTime createdAt, boolean isStaff, boolean bot, boolean inGuild, boolean pending, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.avatarHash = avatarHash;
        this.guildAvatarHash = guildAvatarHash;
        this.joinedAt = joinedAt;
        this.createdAt = createdAt;
        this.isStaff = isStaff;
        this.bot = bot;
        this.inGuild = inGuild;
        this.pending = pending;
        this.messages = messages;
    }
}