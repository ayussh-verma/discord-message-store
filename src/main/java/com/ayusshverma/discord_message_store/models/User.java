package com.ayusshverma.discord_message_store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.time.OffsetDateTime;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(length = 64)
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

    @Column(columnDefinition = "jsonb", name = "public_flags", nullable = false)
    private String publicFlags = "{}"; // Assuming JSON stored as String. Adjust based on actual usage.

    @Column(nullable = false)
    private boolean pending = false;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Message> messages;
}