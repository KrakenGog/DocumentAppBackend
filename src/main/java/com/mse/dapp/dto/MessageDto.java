package com.mse.dapp.dto;

public class MessageDto {
    public Long id;
    public String author;
    public String text;
    public MessageDto(Long id, String author, String text) {
        this.id = id; this.author = author; this.text = text;
    }
}
