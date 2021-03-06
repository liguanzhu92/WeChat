package com.example.guanzhuli.wechat.model;

import java.io.Serializable;

/**
 *  POJO class for tweet sender
 *
 *  Author: Guanzhu Li
 */
public class Sender implements Serializable {
    String username;
    String nick;
    String avatar;

    public String getUsername() {
        return username;
    }

    public String getNick() {
        return nick;
    }

    public String getAvatar() {
        return avatar;
    }
}
