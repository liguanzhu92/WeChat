package com.example.guanzhuli.wechat.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guanzhuli.wechat.R;
import com.example.guanzhuli.wechat.model.Comment;
import com.example.guanzhuli.wechat.model.Image;
import com.example.guanzhuli.wechat.model.Tweet;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetItemHolder>{
    private List<Tweet> tweetList;

    public TweetAdapter(List<Tweet> tweetList) {
        this.tweetList = tweetList;
    }

    @NonNull
    @Override
    public TweetItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_item, parent, false);
        return new TweetItemHolder(v);
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TweetItemHolder holder, int position) {
        Tweet tweet = tweetList.get(position);
        if (tweet.getSender() != null && tweet.getSender().getNick() != null) {
            holder.name.setText(tweet.getSender().getNick());
            Picasso.with(holder.itemView.getContext())
                    .load(tweet.getSender().getAvatar())
                    .into(holder.avatar);
        }
        if (tweet.getContent() != null) {
            holder.content.setText(tweet.getContent());
        }
        if (tweet.getImages() != null && tweet.getImages().size() > 0) {
            addTweetImage(tweet.getImages(), holder);
        }
        if (tweet.getComments() != null) {
            addComment(tweet.getComments(), holder);
        }

    }

    private void addTweetImage(List<Image> images, RecyclerView.ViewHolder holder) {
        if (holder instanceof TweetItemHolder) {
            ImageView imageView;
            for (int i = 0; i < images.size(); i++) {
                switch (i) {
                    case 1:
                    case 2:
                    case 3:
                        ((TweetItemHolder) holder).imageLiner1.setVisibility(View.VISIBLE);
                        imageView = addImageView(holder.itemView.getContext());
                        ((TweetItemHolder) holder).imageLiner1.addView(imageView);
                        Picasso.with(holder.itemView.getContext())
                                .load(images.get(i).getUrl())
                                .into(imageView);
                        break;
                    case 4:
                    case 5:
                    case 6:
                        ((TweetItemHolder) holder).imageLiner2.setVisibility(View.VISIBLE);
                        imageView = addImageView(holder.itemView.getContext());
                        ((TweetItemHolder) holder).imageLiner2.addView(imageView);
                        Picasso.with(holder.itemView.getContext())
                                .load(images.get(i).getUrl())
                                .into(imageView);
                        break;
                    case 7:
                    case 8:
                    case 9:
                        ((TweetItemHolder) holder).imageLiner3.setVisibility(View.VISIBLE);
                        imageView = addImageView(holder.itemView.getContext());
                        ((TweetItemHolder) holder).imageLiner3.addView(imageView);
                        Picasso.with(holder.itemView.getContext())
                                .load(images.get(i).getUrl())
                                .into(imageView);
                        break;
                }
            }
        }
    }

    private ImageView addImageView(Context context) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(0,5,0,5);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    private void addComment(List<Comment> comments, RecyclerView.ViewHolder holder) {
        if (holder instanceof TweetItemHolder) {
            for (int i = 0; i < comments.size(); i++) {
                ((TweetItemHolder)holder).commentLiner.addView(addCommentView(holder.itemView.getContext(), comments.get(i)));
            }
        }
    }

    private View addCommentView(Context context, Comment comment) {
        View v =  LayoutInflater.from(context)
                .inflate(R.layout.comment_item, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);
        TextView name = v.findViewById(R.id.comment_name);
        name.setText(comment.getSender().getNick());
        TextView content = v.findViewById(R.id.comment_content);
        content.setText(comment.getContent());
        return v;
    }

    class TweetItemHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView content;
        LinearLayout imageLiner1;
        LinearLayout imageLiner2;
        LinearLayout imageLiner3;
        LinearLayout commentLiner;

        public TweetItemHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.friend_avatar);
            name = itemView.findViewById(R.id.friend_name);
            content = itemView.findViewById(R.id.friend_tweet_content);
            imageLiner1 = itemView.findViewById(R.id.linear1_friend_tweet_image);
            imageLiner2 = itemView.findViewById(R.id.linear2_friend_tweet_image);
            imageLiner3 = itemView.findViewById(R.id.linear3_friend_tweet_image);
            commentLiner = itemView.findViewById(R.id.linear_friend_tweet_comment);
        }
    }
}

