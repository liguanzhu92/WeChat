package com.example.guanzhuli.wechat.model;

import java.io.Serializable;

public class Comment implements Serializable {
    String content;
    Sender sender;

    public String getContent() {
        return content;
    }

    public Sender getSender() {
        return sender;
    }
}
