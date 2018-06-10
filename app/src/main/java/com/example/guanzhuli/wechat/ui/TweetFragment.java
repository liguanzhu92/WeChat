package com.example.guanzhuli.wechat.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guanzhuli.wechat.R;
import com.example.guanzhuli.wechat.model.Tweet;
import com.example.guanzhuli.wechat.network.NetworkApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TweetFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    List<Tweet> tweetList = new ArrayList<>();
    public TweetFragment() {
        // Requires empty public constructor
    }

    public static TweetFragment newInstance() {
        return new TweetFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        network();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tweet, container, false);
        mRecyclerView = root.findViewById(R.id.recycler_tweet_list);
        linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(new ColorDrawable(getResources().getColor(R.color.fade_view_color)));
        mRecyclerView.addItemDecoration(decoration);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecyclerView.setAdapter(new TweetAdapter(tweetList));
    }

    private void network() {
        Call<List<Tweet>> tweetCall = NetworkApi.getClient().create(NetworkApi.Tweets.class).getTweets();
        tweetCall.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                List<Tweet> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    Tweet tweet = list.get(i);
                    if (tweet.getSender() == null
                            || tweet.getSender().getNick() == null
                            || tweet.getSender().getAvatar() == null
                            || tweet.getSender().getUsername() == null) {
                        continue;
                    }
                    if (tweet.getContent() == null && tweet.getImages() == null) {
                        continue;
                    }
                    tweetList.add(tweet);
                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {

            }
        });
    }
}
