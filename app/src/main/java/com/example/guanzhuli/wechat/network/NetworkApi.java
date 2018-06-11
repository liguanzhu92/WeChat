package com.example.guanzhuli.wechat.network;

import com.example.guanzhuli.wechat.model.Profile;
import com.example.guanzhuli.wechat.model.Tweet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Network calling api
 *
 * Author: Guanzhu Li
 */
public class NetworkApi {
    private static final String BASE_URL = "http://thoughtworks-ios.herokuapp.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
    public interface User {
        @GET("user/jsmith/")
        Call<Profile> getProfile();
    }

    public interface Tweets {
        @GET("user/jsmith/tweets")
        Call<List<Tweet>> getTweets();
    }
}
