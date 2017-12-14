package com.example.anu.todolist.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Design on 14-12-2017.
 */

public class TaskContentProvider extends ContentProvider {

    public TaskDbHelper mTaskDbHelper;

    /**
     * define final integer constants for the directories and for single item
     * convention is to use, 100, 200, 300 etc for directories
     * and related ints 101, 102 etc for items in it
     */
    public static final int TASKS = 100;
    public static final int TASKS_WITH_ID = 101;

    /**
     * create uri matcher
     * variable name starts with small 's' because it is static
     */
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    /**
     * method to create UriMatcher
     * @return created UriMatcher
     */
    private static UriMatcher buildUriMatcher() {
        /**
         * deefine a uri matcher with no match by passing UriMatcher.NO_MATCH to the constructor
         */
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /**
         * add match for each of the uri that we want to access
         */
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.PATH_TASKS, TASKS);  //for the entire directory
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.PATH_TASKS, TASKS);  //for single item in the directory

        return uriMatcher;
    }

    /**
     * method to initialize {@link TaskContentProvider}
     * initialie anything we need to setup and accessthe underlying data source
     * initialize the database helper
     * @return true
     */
    @Override
    public boolean onCreate() {
        mTaskDbHelper = new TaskDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
