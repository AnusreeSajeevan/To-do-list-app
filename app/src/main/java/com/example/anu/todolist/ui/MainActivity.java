package com.example.anu.todolist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anu.todolist.R;
import com.example.anu.todolist.adapter.TaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycleriew_tasks)
    RecyclerView recycleriewTasks;

    private TaskAdapter mTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupRecuclerView();
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
}
