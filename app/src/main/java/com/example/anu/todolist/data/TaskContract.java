package com.example.anu.todolist.data;

/**
 * Created by Design on 14-12-2017.
 */

import android.provider.BaseColumns;

/**
 * class defines the contract of the database tables and it's columns
 */
public class TaskContract {

    /**
     * inner class for the table task
     */
    public class TaskEntry implements BaseColumns{

        /* table name */
        public static final String TABLE_NAME = "task";


        /**
         * constants defininf thetable columns
         * a column named _ID will be created automatically
         */
        public static final String KEY_COLUMN_DESCRIPTION = "description";
        public static final String KEY_COLUMN_PRIORITY = "priority";

    }

}
