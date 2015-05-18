package com.example.administrator.taskmanagesys;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {

    @InjectView(R.id.imgbtn_home_search)
    ImageButton imgbtnHomeSearch;
    @InjectView(R.id.tv_actionbar_title)
    EditText tvActionbarTitle;
    @InjectView(R.id.imbtn_actionbar_function1)
    ImageButton imbtnActionbarFunction1;
    @InjectView(R.id.lv_task_list)
    ListView lvTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


    }


}
