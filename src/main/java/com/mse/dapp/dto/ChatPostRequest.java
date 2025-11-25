package com.mse.dapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatPostRequest {
    private String text;
    private String recipient; // null для общего чата
}
