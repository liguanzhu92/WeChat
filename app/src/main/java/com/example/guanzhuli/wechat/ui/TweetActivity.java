package com.example.guanzhuli.wechat.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanzhuli.wechat.R;
import com.example.guanzhuli.wechat.model.Profile;
import com.example.guanzhuli.wechat.model.Tweet;
import com.example.guanzhuli.wechat.util.FileHelper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class TweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        TweetFragment tweetFragment =
                (TweetFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (tweetFragment == null) {
            tweetFragment = TweetFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, tweetFragment);
            transaction.commit();
        }
        setProfileContent();
    }

    private void setProfileContent() {
        // load profile
        Gson gson = new Gson();
        Profile profile = gson.fromJson(FileHelper.readFile(getApplicationContext(), R.raw.profile), Profile.class);
        TextView name = findViewById(R.id.user_name);
        name.setText(profile.getNick());
        ImageView avatar = findViewById(R.id.user_avatar);
        Picasso.with(getApplicationContext()).load(profile.getAvatar()).into(avatar);
        ImageView profileImage = findViewById(R.id.user_profile_image);
        Picasso.with(getApplicationContext()).load(profile.getProfileimage()).into(profileImage);
    }
}
