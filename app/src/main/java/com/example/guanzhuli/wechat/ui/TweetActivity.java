package com.example.guanzhuli.wechat.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanzhuli.wechat.R;
import com.example.guanzhuli.wechat.model.Profile;
import com.example.guanzhuli.wechat.network.NetworkApi;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TweetActivity extends AppCompatActivity{

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
        fetchProfile();
    }

    private void fetchProfile() {
        Call<Profile> profileCall = NetworkApi.getClient().create(NetworkApi.User.class).getProfile();
        profileCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                setProfileContent(response.body());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setProfileContent(Profile profile) {
        TextView name = findViewById(R.id.user_name);
        name.setText(profile.getNick());
        ImageView avatar = findViewById(R.id.user_avatar);
        Picasso.with(getApplicationContext()).load(profile.getAvatar()).into(avatar);
        ImageView profileImage = findViewById(R.id.user_profile_image);
        Picasso.with(getApplicationContext()).load(profile.getProfileimage()).into(profileImage);
    }
}
