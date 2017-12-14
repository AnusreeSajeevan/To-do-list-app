package com.example.anu.todolist.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.anu.todolist.R;
import com.example.anu.todolist.utils.TaskUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskAddActivity extends AppCompatActivity {

    @BindView(R.id.txt_task_description)
    EditText txtTaskDescription;
    @BindView(R.id.rg_high)
    RadioButton rgHigh;
    @BindView(R.id.rg_medium)
    RadioButton rgMedium;
    @BindView(R.id.rg_low)
    RadioButton rgLow;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    private String priority = TaskUtils.PRIORITY_HIGH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        ButterKnife.bind(this);

        //set prority high by default
        radioGroup.check(R.id.rg_high);
    }

    /**
     * method called when clicking any of the priority radio buttons
     * to set the task priority
     * @param view rafarence to the clicked button
     */
    public void setPriority(View view) {
        int clickedId = view.getId();
        switch (clickedId) {
            case R.id.rg_high:
                priority = TaskUtils.PRIORITY_HIGH;
                break;
            case R.id.rg_medium:
                priority = TaskUtils.PRIORITY_MEDIUM;
                break;
            case R.id.rg_low:
                priority = TaskUtils.PRIORITY_LOW;
                break;
        }
    }

    /**
     * method called on clicking "ADD TASK" button
     */
    @OnClick(R.id.btn_add)
    public void onAddClicked() {
        Toast.makeText(this, priority, Toast.LENGTH_SHORT).show();
    }
}
