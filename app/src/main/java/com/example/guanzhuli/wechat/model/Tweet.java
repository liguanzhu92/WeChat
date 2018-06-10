package com.example.guanzhuli.wechat.model;

import java.io.Serializable;
import java.util.List;

public class Tweet implements Serializable {
    String content;
    Sender sender;
    List<Image> images;
    List<Comment> comments;

    public String getContent() {
        return content;
    }

    public Sender getSender() {
        return sender;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
