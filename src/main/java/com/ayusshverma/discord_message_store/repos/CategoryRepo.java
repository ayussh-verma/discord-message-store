package com.ayusshverma.discord_message_store.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayusshverma.discord_message_store.user.UserEntity;

@Repository
public interface CategoryRepo extends JpaRepository<UserEntity, String> {
}