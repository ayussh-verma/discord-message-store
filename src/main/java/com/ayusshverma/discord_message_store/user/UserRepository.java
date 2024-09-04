package com.ayusshverma.discord_message_store.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepo extends JpaRepository<UserEntity, String> {
    @Modifying
    @Query("DELETE FROM UserEntity u WHERE u.id = :id")
    int deleteByIdReturningCount(String id);
}
