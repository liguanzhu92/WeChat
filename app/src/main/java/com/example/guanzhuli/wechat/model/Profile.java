package com.example.guanzhuli.wechat.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *  POJO class for logged user profile
 *
 *  Author: Guanzhu Li
 */
public class Profile implements Serializable {
    @SerializedName("profile-image")
    private String profileimage;
    private String avatar;
    private String nick;
    private String username;

    public String getProfileimage() {
        return profileimage;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNick() {
        return nick;
    }

    public String getUsername() {
        return username;
    }
}
