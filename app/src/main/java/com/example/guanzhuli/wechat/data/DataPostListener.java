package com.example.guanzhuli.wechat.data;

import com.example.guanzhuli.wechat.model.Tweet;

import java.util.List;

public interface DataPostListener {

    void postResult(List<Tweet> tweets);
}
