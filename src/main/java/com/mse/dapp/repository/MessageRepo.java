package com.mse.dapp.repository;

import com.mse.dapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

    // История общего чата
    List<Message> findByRecipientIsNullOrderByCreatedAtAsc();

    // История личного диалога
    @Query("SELECT m FROM Message m WHERE " +
           "(m.author = :user1 AND m.recipient = :user2) OR " +
           "(m.author = :user2 AND m.recipient = :user1) " +
           "ORDER BY m.createdAt ASC")
    List<Message> findConversation(@Param("user1") String user1, @Param("user2") String user2);
}
