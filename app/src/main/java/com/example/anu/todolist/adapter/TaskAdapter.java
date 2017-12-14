package com.example.anu.todolist.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anu.todolist.R;
import com.example.anu.todolist.data.TaskContract;

import java.text.RuleBasedCollator;

import butterknife.BindView;

/**
 * Created by Design on 13-12-2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

    private Cursor mCursor;
    private Context mContext;

    public TaskAdapter(Context context) {
        mContext = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_task_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.txtTaskDescription.setText(mCursor.getString(mCursor.getColumnIndex(TaskContract.TaskEntry.KEY_COLUMN_DESCRIPTION)));
        int priority = mCursor.getInt(mCursor.getColumnIndex(TaskContract.TaskEntry.KEY_COLUMN_PRIORITY));

        /**
         * set the priority text and color based on the priority
         */
        int priorityColor = getPriorityColor(priority);
        GradientDrawable priorityBackground = (GradientDrawable) holder.txtTaskPriority.getBackground();
        priorityBackground.setColor(priorityColor);
        holder.txtTaskPriority.setText(priority);
    }

    private int getPriorityColor(int priority) {
        int color = 0;
        switch (priority){
            case 1:
                color = mContext.getResources().getColor(R.color.priority_high);
                break;
            case 2:
                color = mContext.getResources().getColor(R.color.priority_medium);
                break;
            case 3:
                color = mContext.getResources().getColor(R.color.priority_low);
                break;
                default:
                    break;
        }
        return color;
    }

    @Override
    public int getItemCount() {
        //   if(mCursor.getCount() == 0)
        return 0;
        //  else
        //      return mCursor.getCount();
    }
}
