package com.example.anu.todolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Design on 14-12-2017.
 */

public class TaskDbHelper extends SQLiteOpenHelper {

    private static String KEY_DATABASE_NAME = "tasks.db";
    private static int KEY_DATABASE_VERSION = 1;

    public TaskDbHelper(Context context) {
        super(context, KEY_DATABASE_NAME, null, KEY_DATABASE_VERSION);
    }

    /**
     * method responsible for creating the table for the first time
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String QUERY_CREATE_TTASK_TABLE = "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " (" +
                TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.TaskEntry.KEY_COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                TaskContract.TaskEntry.KEY_COLUMN_PRIORITY + " INTEGER NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(QUERY_CREATE_TTASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME);
    }
}
