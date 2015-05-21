package com.example.administrator.taskmanagesys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import beans.Task;
import butterknife.ButterKnife;
import butterknife.InjectView;
import util.DataManagerHelper;
import util.ImageThumbnail;


public class AddTaskActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.tv_title_topbar)
    TextView tvTitleTopbar;
    @InjectView(R.id.imgbtn_take_photo)
    ImageButton imgbtnTakePhoto;
    @InjectView(R.id.edit_top)
    RelativeLayout editTop;
    @InjectView(R.id.et_task_name)
    EditText etTaskName;
    @InjectView(R.id.edit_title)
    RelativeLayout editTitle;
    @InjectView(R.id.et_task_details)
    EditText etTaskDetails;
    @InjectView(R.id.edit_content)
    RelativeLayout editContent;
    @InjectView(R.id.sp_type_choose)
    Spinner spTypeChoose;
    @InjectView(R.id.edit_choose)
    RelativeLayout editChoose;
    @InjectView(R.id.sp_type_weigh_choose)
    Spinner spTypeWeighChoose;
    @InjectView(R.id.edit_weigh_choose)
    RelativeLayout editWeighChoose;
    @InjectView(R.id.iv_show_photo)
    ImageView ivShowPhoto;
    @InjectView(R.id.layout_edit_photo)
    RelativeLayout layoutEditPhoto;
    @InjectView(R.id.bt_save_task)
    Button btSaveTask;

    Bitmap bitmap, mBitmap;
    String taskEditName = null;
    int toDoWhat = 0;
    Task tempTask = null;
    Intent cameraIntent = null;
    Uri imageUri = null;
    int task_change = 0;
    int type_weigh_change = 0;
    public static final int word_task = 1;
    public static final int test_task = 2;
    public static final int class_task = 3;
    DataManagerHelper dbManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.inject(this);
        Bundle bundle = this.getIntent().getExtras();
        toDoWhat = Integer.parseInt(bundle.getString("toDoWhat"));
        taskEditName = bundle.getString("taskName");
        dbManage = new DataManagerHelper(getApplicationContext());
        SpinnerInit();
        if (toDoWhat == 1) {

            tempTask = dbManage.getItemFromDB(taskEditName);
            etTaskName.setText(tempTask.getName());
            etTaskDetails.setText(tempTask.getDescribe());
            tvTitleTopbar.setText("修改任务");
            spTypeChoose.setSelection(tempTask.getTaskType(), true);
            spTypeWeighChoose.setSelection(tempTask.getTaskweigh(), true);
            showThePicture();
        }

        btSaveTask.setOnClickListener(this);
        imgbtnTakePhoto.setOnClickListener(this);
    }

    private void SpinnerInit() {
        String[] taskName = {"单词复习任务", "考试复习任务", "课堂复习任务"};
        ArrayList<String> taskNames = new ArrayList<String>();
        for (int i = 0; i < taskName.length; i++) {
            taskNames.add(taskName[i]);
        }
        ArrayAdapter<String> aspnCountries = new ArrayAdapter<String>(this,
                                                                                 android.R.layout.simple_spinner_item, taskNames); // ��ʼ��������
        aspnCountries.setDropDownViewResource(R.layout.drop_down_item);
        spTypeChoose.setAdapter(aspnCountries);
        spTypeChoose.setPromptId(R.string.prompt);
        spTypeChoose
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                            task_change = arg2;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            Toast.makeText(getApplicationContext(), "haha",
                                                      Toast.LENGTH_LONG).show();
                        }
                    });

        String[] taskweigh = {"很重要很紧急", "很重要不紧急", "不重要不紧急", "不重要不紧急"};
        ArrayList<String> taskweighs = new ArrayList<String>();
        for (int i = 0; i < taskweigh.length; i++) {
            taskweighs.add(taskweigh[i]);
        }
        ArrayAdapter<String> aspnweigh = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, taskweighs);
        aspnweigh.setDropDownViewResource(R.layout.drop_down_item);
        spTypeWeighChoose.setAdapter(aspnweigh);
        spTypeWeighChoose.setPromptId(R.string.prompt);
        spTypeWeighChoose
                    .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                            type_weigh_change = arg2;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {

                            Toast.makeText(getApplicationContext(), "haha",
                                                      Toast.LENGTH_LONG).show();
                        }
                    });


    }

    public void showThePicture() {
        layoutEditPhoto.setVisibility(View.VISIBLE);
        String path = tempTask.getPicture();
        Bitmap tempBitmap = BitmapFactory.decodeFile(path);
        if (null != tempBitmap) {
            int scale = ImageThumbnail.reckonThumbnail(tempBitmap.getWidth(),
                                                                  tempBitmap.getHeight(), 500, 600);
            bitmap = ImageThumbnail.PicZoom(tempBitmap,
                                                           tempBitmap.getWidth() / scale, tempBitmap.getHeight() / scale);
            tempBitmap.recycle();
            ivShowPhoto.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            layoutEditPhoto.setVisibility(View.VISIBLE);
            Bitmap tempBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()
                                                                 + "/workupload.jpg");
            Log.v("a", imageUri.toString());
            if (null != tempBitmap) {
                int scale = ImageThumbnail.reckonThumbnail(tempBitmap.getWidth(),
                                                                      tempBitmap.getHeight(),
                                                                      500, 600);

                bitmap = ImageThumbnail.PicZoom(tempBitmap,
                                                               tempBitmap.getWidth() / scale,
                                                               tempBitmap.getHeight() / scale);

                tempBitmap.recycle();

                ivShowPhoto.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent jumpToMessageActivity = new Intent();
            jumpToMessageActivity.setClass(getApplicationContext(),
                                                      MainActivity.class);
            AddTaskActivity.this.startActivity(jumpToMessageActivity);
            AddTaskActivity.this.finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.bt_save_task) {

            String taskname = etTaskName.getText().toString();
            String taskdesc = etTaskDetails.getText().toString();
            String fileName = "";
            if (taskname.equals("")) {
                Toast.makeText(getApplicationContext(), "请输入任务信息",
                                          Toast.LENGTH_SHORT).show();
                return;
            }
            if( dbManage.getItemFromDB(taskname) != null)
            {
                Toast.makeText(getApplicationContext(), "已存在同名任务",
                                          Toast.LENGTH_SHORT).show();
                return;
            }


            File judgeFile = new File(Environment.getExternalStorageDirectory(), "workupload.png");
            if (imageUri != null && judgeFile.exists()) {
                System.out.println(judgeFile.exists());
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                    return;
                }
                FileOutputStream b = null;
                String filename = getResources().getString( R.string.filename);
                File file = new File(filename);
                file.mkdirs();
                Random hehe = new Random();
                Log.v("a", "3");
                fileName = filename + taskname + hehe.nextInt() + ".png";
                try {
                    b = new FileOutputStream(fileName);
                    Log.v("a", "4");
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        Log.v("a", "5");
                        b.flush();
                        b.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "照片保存成功",
                                              Toast.LENGTH_LONG).show();
                }
            }

            DataManagerHelper save = new DataManagerHelper(AddTaskActivity.this);
            if (toDoWhat == 0) {
                tempTask = new Task(taskname, taskdesc, fileName,
                                               type_weigh_change);
                tempTask.setTaskType(task_change);
                save.saveTask(tempTask);
                Toast.makeText(getApplicationContext(), "数据存入成功",
                                          Toast.LENGTH_LONG).show();
            } else {
                tempTask.setName(taskname);
                tempTask.setDescribe(taskdesc);
                tempTask.setTaskType(task_change);
                tempTask.setPicture(fileName);
                tempTask.setTaskweigh(type_weigh_change);
                save.modifyItem(tempTask, taskEditName);
                Toast.makeText(getApplicationContext(), "数据修改成功",
                                          Toast.LENGTH_LONG).show();
            }

            Intent intent = new Intent();
            intent.setClass(AddTaskActivity.this, MainActivity.class);
            AddTaskActivity.this.startActivity(intent);
            AddTaskActivity.this.finish();

        } else if (id == R.id.imgbtn_take_photo) {
            cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "workupload.png"));
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, 1);
        }

    }

    @Override
    public void finish() {
        super.finish();
        dbManage.close();
    }
}
