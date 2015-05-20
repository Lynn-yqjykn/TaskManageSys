package com.example.administrator.taskmanagesys;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import adapter.TaskAdapter;
import beans.Task;
import butterknife.ButterKnife;
import butterknife.InjectView;
import util.DBManageHelper;
import util.DataManagerHelper;

public class MainActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.imgbtn_home_search)
    ImageButton imgBtnHomeSearch;
    @InjectView(R.id.tv_actionbar_title)
    EditText tvActionbarTitle;
    @InjectView(R.id.imgbtn_add_task)
    ImageButton imgBtnAddTask;
    @InjectView(R.id.lv_task_list)
    ListView lvTaskList;
    TaskAdapter mAdapter;
    DataManagerHelper mDataManager;

    private ArrayList<Task> taskArrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mDataManager = new DataManagerHelper(getApplicationContext());
        taskArrayList = mDataManager.getAllTaskArray();

        mAdapter = new TaskAdapter(getApplicationContext(), taskArrayList);
        lvTaskList.setAdapter(mAdapter);
        lvTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        lvTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确认要删除这条记录吗");
                builder.setPositiveButton("确认", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mDataManager.deleteItem(taskArrayList.get(position));
                        taskArrayList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return false;
            }
        });
        imgBtnAddTask.setOnClickListener(this);
        imgBtnHomeSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        switch (vId) {
            case R.id.imgbtn_home_search:
                String searchString = tvActionbarTitle.getText().toString();
                Task task = mDataManager.getItemFromDB(searchString);
                if(task == null)
                {
                    Toast.makeText(getApplicationContext(),"未找到相应的任务",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    taskArrayList.clear();
                    taskArrayList.add(task);
                    mAdapter.notifyDataSetChanged();
                }
                break;

            case R.id.imgbtn_add_task:
                Intent jumpToEdit = new Intent();
                jumpToEdit.setClass(MainActivity.this, AddTaskActivity.class);
                Bundle dataForEdit = new Bundle();
                dataForEdit.putString("toDoWhat", "0");
                dataForEdit.putString("taskName", "");
                jumpToEdit.putExtras(dataForEdit);
                MainActivity.this.startActivity(jumpToEdit);
                MainActivity.this.finish();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        mDataManager.close();
    }
}
