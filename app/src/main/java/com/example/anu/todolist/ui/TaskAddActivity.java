package com.example.anu.todolist.ui;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.anu.todolist.R;
import com.example.anu.todolist.data.TaskContract;
import com.example.anu.todolist.utils.TaskUtils;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskAddActivity extends AppCompatActivity implements com.mobsandgeeks.saripaar.Validator.ValidationListener{

    @Required(order = 1, message = "Enter task descrition")
    @BindView(R.id.et_task_description)
    EditText etTaskDescription;
    @BindView(R.id.rg_high)
    RadioButton rgHigh;
    @BindView(R.id.rg_medium)
    RadioButton rgMedium;
    @BindView(R.id.rg_low)
    RadioButton rgLow;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    private int priority = TaskUtils.PRIORITY_HIGH;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        ButterKnife.bind(this);

        //set prority high by default
        radioGroup.check(R.id.rg_high);

        validator = new Validator(this);
        validator.setValidationListener(this);
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
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        addTask();
    }

    private void addTask() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskContract.TaskEntry.KEY_COLUMN_DESCRIPTION, etTaskDescription.getText().toString());
        contentValues.put(TaskContract.TaskEntry.KEY_COLUMN_PRIORITY, priority);
        Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);
        if (null != uri){
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        if (failedView instanceof EditText){
            EditText editText = (EditText) failedView;
            editText.setError(failedRule.getFailureMessage());
        }
    }
}
