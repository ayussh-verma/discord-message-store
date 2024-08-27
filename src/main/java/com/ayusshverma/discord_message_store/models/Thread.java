package com.ayusshverma.discord_message_store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import java.util.List;
import java.time.OffsetDateTime;

import org.hibernate.mapping.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "threads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread {
    @Id
    @Column(length = 64)
    private String id;

    @ManyToOne
    @JoinColumn(name = "parent_channel_id", nullable = false, foreignKey = @ForeignKey(name = "parent_channel_fk"))
    private Channel parentChannel;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived;

    @Column(name = "auto_archive_duration", nullable = false)
    private int autoArchiveDuration;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked;

    @Column(length = 64, nullable = false)
    private String type;

    @OneToMany(mappedBy = "thread", orphanRemoval = true)
    private List<Message> messages;
}