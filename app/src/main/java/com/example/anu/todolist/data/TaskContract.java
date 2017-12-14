package com.example.anu.todolist.data;

/**
 * Created by Design on 14-12-2017.
 */

import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URL;

/**
 * class defines the contract of the database tables and it's columns
 * designed to keep track of constants that help us access data in a given database
 */
public class TaskContract {

    /*Add content provider constants to the Contract
    Clients need to know how to access the task data, and it's your job to provide
    these content URI's for the path to that data:
            1) Content authority,
            2) Base content URI,
            3) Path(s) to the tasks directory
            4) Content URI for data in the TaskEntry class*/

    /**
     * the authority, which is how the code knows which content provider to access
     */
    public static final String AUTHORITY = "com.example.anu.todolist";

    /**
     * BASE_CONTENT_URI = "content://" + {@literal AUTHORITY}
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    /* define possible paths to access data in this contract */
    /**
     * path to access the entire "tasks" directory
     */
    public static final String PATH_TASKS = "tasks";

    /**
     * inner class for the table task
     */
    public static class TaskEntry implements BaseColumns{

        /* table name */
        public static final String TABLE_NAME = "task";

        /**
         * BASE_CONTENT_URI + PATH_TASKS
         */
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();


        /**
         * constants defininf thetable columns
         * a column named _ID will be created automatically
         */
        public static final String KEY_COLUMN_DESCRIPTION = "description";
        public static final String KEY_COLUMN_PRIORITY = "priority";

    }

}
