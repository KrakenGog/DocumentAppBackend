package com.mse.dapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    // Если null -> общий чат, иначе -> логин получателя
    @Column(name = "recipient")
    private String recipient;

    @Column(nullable = false, length = 2000)
    private String text;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
