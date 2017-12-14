package com.example.anu.todolist.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
    public static UriMatcher buildUriMatcher() {
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
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selecionArgs,
                        @Nullable String sortOrder) {
        Cursor returnCursor;
        SQLiteDatabase sqLiteDatabase = mTaskDbHelper.getReadableDatabase();
        int uriMatch = sUriMatcher.match(uri);
        switch (uriMatch){
            case TASKS:
                returnCursor = sqLiteDatabase.query(TaskContract.TaskEntry.TABLE_NAME,
                        projection, selection, selecionArgs, null, null, sortOrder);
                break;
                default:
                    throw new UnsupportedOperationException("unknown uri");
        }

        //set notification uri on th cursor
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    /**
     * method to insert task into the database
     * @param uri
     * @param contentValues
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri returnUri;
        SQLiteDatabase sqLiteDatabase = mTaskDbHelper.getWritableDatabase();
        //identify the uri match
        int uriMatch = sUriMatcher.match(uri);
        switch (uriMatch){
            case TASKS:
                long id = sqLiteDatabase.insert(TaskContract.TaskEntry.TABLE_NAME, null, contentValues);
                if (id>0){
                    returnUri = ContentUris.withAppendedId(TaskContract.TaskEntry.CONTENT_URI, id);
                }else {
                    throw new SQLiteException("Cannot insert task");
                }
                break;
                default:
                    throw new UnsupportedOperationException("unknown uri : "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
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
