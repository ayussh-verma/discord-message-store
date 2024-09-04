package com.ayusshverma.discord_message_store.entities_base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdBaseEntity {
    @Id
    @Column(length = 19, name = "id")
    private String id;
}
