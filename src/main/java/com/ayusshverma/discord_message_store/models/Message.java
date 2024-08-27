package com.ayusshverma.discord_message_store.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Index;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ConstraintMode;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "messages", indexes = {
        @Index(columnList = "channel_id"),
        @Index(columnList = "thread_id"),
        @Index(columnList = "author_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @Column(length = 64)
    private String id;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false, foreignKey = @ForeignKey(name = "channel_fk", value = ConstraintMode.CONSTRAINT))
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "thread_id", nullable = true, foreignKey = @ForeignKey(name = "thread_fk", value = ConstraintMode.CONSTRAINT))
    private Thread thread;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, foreignKey = @ForeignKey(name = "author_fk", value = ConstraintMode.CONSTRAINT))
    private User author;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @PrePersist
    @PreUpdate
    private void validateCreatedAt() {
        if (createdAt.isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("createdAt cannot be in the future");
        }
    }
}