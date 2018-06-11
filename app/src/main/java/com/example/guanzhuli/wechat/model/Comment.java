package com.example.guanzhuli.wechat.model;

import java.io.Serializable;

/**
<<<<<<< Updated upstream
 *  POJO class for Comment in tweet
 *
 *  Author: Guanzhu Li
=======
 * A data model to save Comment
 *
 * Author: Guanzhu Li
>>>>>>> Stashed changes
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
