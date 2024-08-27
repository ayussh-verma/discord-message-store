package com.ayusshverma.discord_message_store.repos;

import com.ayusshverma.discord_message_store.models.User;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = ?1")
    int deleteByIdReturningCount(String id);
}