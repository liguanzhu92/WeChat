package com.example.guanzhuli.wechat.model;

import java.io.Serializable;

/**
 *  POJO class for Comment in tweet
 *
 *  Author: Guanzhu Li
 */
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
