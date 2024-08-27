package com.ayusshverma.discord_message_store.repos;

import com.ayusshverma.discord_message_store.models.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepo extends JpaRepository<Thread, String> {
}