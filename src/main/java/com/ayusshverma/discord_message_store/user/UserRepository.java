package com.ayusshverma.discord_message_store.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepo extends JpaRepository<UserEntity, String> {
    
}
