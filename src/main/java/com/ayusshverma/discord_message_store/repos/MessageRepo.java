package com.ayusshverma.discord_message_store.repos;

import com.ayusshverma.discord_message_store.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, String>  {
}