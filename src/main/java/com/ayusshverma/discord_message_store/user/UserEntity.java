package com.ayusshverma.discord_message_store.user;

import java.time.OffsetDateTime;

import com.ayusshverma.discord_message_store.entities_base.NamedBaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
class UserEntity extends NamedBaseEntity {
     @Column(name = "avatar_hash", nullable = true, length = 200)
    private String avatarHash;

    @Column(name = "guild_avatar_hash", nullable = true, length = 200)
    private String guildAvatarHash;

    @Column(name = "joined_at", nullable = true)
    private OffsetDateTime joinedAt;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "bot", nullable = false)
    @ColumnDefault("false")
    private boolean bot = false;

    @Column(name = "in_guild", nullable = false)
    @ColumnDefault("true")
    private boolean inGuild = true;
}
