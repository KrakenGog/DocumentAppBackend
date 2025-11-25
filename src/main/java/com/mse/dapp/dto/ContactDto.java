package com.mse.dapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private String username;
    private String lastMessage;
    private Instant lastMessageTime;
    private boolean online;
}
