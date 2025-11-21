package com.mse.dapp.dto;

public class WsPayloads {
    public static class ClientSend {
        public String type;
        public String text;
    }
    public static class ServerAck {
        public String type = "ack";
        public Long ur_id;
        public ServerAck(Long id) { this.ur_id = id; }
    }
    public static class ServerMessage {
        public String type = "message";
        public Long id;
        public String author;
        public String text;
        public ServerMessage(Long id, String author, String text) {
            this.id = id; this.author = author; this.text = text;
        }
    }
}