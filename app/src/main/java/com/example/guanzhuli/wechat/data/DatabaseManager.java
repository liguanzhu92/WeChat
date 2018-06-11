package com.example.guanzhuli.wechat.data;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;

import java.io.File;

/**
 * Couchbase Util to manager data
 *
 * Author: Guanzhu Li
 */
public class DatabaseManager {
    private static final String DB_NAME = "tweetdb";
    public Database database;

    private static DatabaseManager instance = null;

    private DatabaseManager(Context context) {
        try {
            DatabaseConfiguration config = new DatabaseConfiguration(context);
            File dir = context.getCacheDir();
            config.setDirectory(dir.toString());
            database = new Database(DB_NAME, config);
        }
        catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseManager getSharedInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }
}
