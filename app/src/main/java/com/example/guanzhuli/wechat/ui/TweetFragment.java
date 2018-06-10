package com.example.guanzhuli.wechat.ui;

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
import com.example.guanzhuli.wechat.util.FileHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TweetFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    public TweetFragment() {
        // Requires empty public constructor
    }

    public static TweetFragment newInstance() {
        return new TweetFragment();
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
        mRecyclerView.setAdapter(new TweetAdapter(loadTweet()));
        return root;
    }


    private List<Tweet> loadTweet() {
        Gson gson = new Gson();
        Tweet[] tweets = gson.fromJson(FileHelper.readFile(getContext(), R.raw.tweet), Tweet[].class);
        List<Tweet> tweetList = new ArrayList<>();
        for (Tweet tweet : tweets) {
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
        return tweetList;
    }
}
