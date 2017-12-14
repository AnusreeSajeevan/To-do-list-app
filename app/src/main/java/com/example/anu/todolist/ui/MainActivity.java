package com.example.anu.todolist.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.anu.todolist.R;
import com.example.anu.todolist.adapter.TaskAdapter;
import com.example.anu.todolist.data.TaskContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.recycleriew_tasks)
    RecyclerView recycleriewTasks;

    private TaskAdapter mTaskAdapter;
    private static int TASK_LOADER_ID = 10;
    private Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRecuclerView();

        getSupportLoaderManager().initLoader(TASK_LOADER_ID, bundle, MainActivity.this);
    }

    /**
     * method to set up recycler view and set adapter to it
     */
    private void setupRecuclerView() {
        recycleriewTasks.setLayoutManager(new LinearLayoutManager(this));
        recycleriewTasks.setHasFixedSize(true);
        mTaskAdapter = new TaskAdapter(this);
        recycleriewTasks.setAdapter(mTaskAdapter);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        Intent i = new Intent(MainActivity.this, TaskAddActivity.class);
        startActivity(i);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mCursor = null;
            @Override
            protected void onStartLoading() {
               if (null == mCursor){
                   forceLoad();
               }else {
                   deliverResult(mCursor);
               }
            }

            @Override
            public Cursor loadInBackground() {

                /**
                 * specify priority as sort order so that tasks will be displayed based on priority
                 */
                return getContentResolver().query(TaskContract.TaskEntry.CONTENT_URI,
                        null, null, null, TaskContract.TaskEntry.KEY_COLUMN_PRIORITY);
            }

            @Override
            public void deliverResult(Cursor data) {
                mCursor = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mTaskAdapter.swapCursor(data);
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.
     * onLoaderReset removes any references this activity had to the loader's data.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTaskAdapter.swapCursor(null);
    }

    /**
     * This method is called after this activity has been paused or restarted.
     * Often, this is after new data has been inserted through an AddTaskActivity,
     * so this restarts the loader to re-query the underlying data for any changes.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // re-queries for all tasks
        getSupportLoaderManager().restartLoader(TASK_LOADER_ID, null, this);
    }
}
