package com.example.anu.todolist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.anu.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Design on 13-12-2017.
 */

public class TaskHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_task_description)
    TextView txtTaskDescription;
    @BindView(R.id.txt_task_priority)
    TextView txtTaskPriority;

    public TaskHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
