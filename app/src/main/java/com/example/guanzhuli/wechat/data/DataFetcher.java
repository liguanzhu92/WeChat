package com.example.guanzhuli.wechat.data;

import android.content.Context;
import android.os.AsyncTask;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Expression;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.guanzhuli.wechat.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Save tweet to couchbase
 *
 * Author: Guanzhu Li
 */
public class DataFetcher extends AsyncTask<Void, Void, List<Tweet>> {
    private Context context;
    private DataPostListener listener;
    private int limit;
    private int offset;

    public DataFetcher(Context context, DataPostListener listener, int limit, int offset) {
        this.context = context;
        this.listener = listener;
        this.limit = limit;
        this.offset = offset;
    }

    public DataFetcher(Context context, DataPostListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<Tweet> doInBackground(Void... voids) {
        List<Tweet> tweetList = new ArrayList<>();
/*        Query query = QueryBuilder.select(SelectResult.all())
                .from(DataSource.database(DatabaseManager.getSharedInstance(context).database))
                .limit(Expression.intValue(limit), Expression.intValue(offset));*/
        Query query = QueryBuilder.select(SelectResult.all())
                .from(DataSource.database(DatabaseManager.getSharedInstance(context).database));
        try {
            ResultSet results = query.execute();
            Result row;
            while ((row = results.next()) != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Dictionary valueMap = row.getDictionary(DatabaseManager.getSharedInstance(context).database.getName());
                Tweet tweet = objectMapper.convertValue(valueMap.toMap(), Tweet.class);
                //Log.i("chestnut", tweet.getContent() == null ? "null" : tweet.getContent());
                tweetList.add(tweet);
            }
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return tweetList;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        listener.postResult(tweets);
    }
}
