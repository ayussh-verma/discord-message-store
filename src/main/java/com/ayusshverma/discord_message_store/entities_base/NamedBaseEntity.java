package com.ayusshverma.discord_message_store.entities_base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class NamedBaseEntity extends IdBaseEntity {
    @Column(length = 200, nullable = false)
    private String name;
}
