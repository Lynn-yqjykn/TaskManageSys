package util;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import beans.Task;


public class DataManagerHelper {
    Context context;
    Cursor cursor;
    DBManageHelper dbManageHelper = null;

    public DataManagerHelper(Context context) {
        this.context = context;
        dbManageHelper = new DBManageHelper(context);

    }

    /**
     *得到填充ListView用的array数据
     */
    public ArrayList<Task> getAllTaskArray() {
        ArrayList<Task> array = new ArrayList<Task>();
        cursor = dbManageHelper.getCursor();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String describe = cursor.getString(cursor.getColumnIndex("describe"));
            String taskCreateTime = cursor.getString(cursor.getColumnIndex("taskCreateTime"));
            String taskToDoTime = cursor.getString(cursor.getColumnIndex("taskToDoTime"));
            int taskType = cursor.getInt(cursor.getColumnIndex("taskType"));
            int taskDelay = cursor.getInt(cursor.getColumnIndex("taskDelay"));
            int taskLevel = cursor.getInt(cursor.getColumnIndex("taskLevel"));
            String picture = cursor.getString(cursor.getColumnIndex("picture"));
            int taskWeight = cursor.getInt(cursor.getColumnIndex("taskWeight"));
            Task task = new Task(taskType, taskDelay, taskLevel, picture, name,
                                            taskCreateTime, taskToDoTime, describe, taskWeight);
            array.add(task);
            cursor.moveToNext();
        }
        return array;
    }

    /**
     * 获取到单个的Task
     */
    public Task getItemFromDB(String taskEditName) {

        String[] tempString = new String[1];
        tempString[0] = taskEditName;
        cursor = dbManageHelper.getItem(tempString);
        if(cursor.getCount()==0)
            return null;
        cursor.moveToFirst();
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String describe = cursor.getString(cursor.getColumnIndex("describe"));
        String taskCreateTime = cursor.getString(cursor.getColumnIndex("taskCreateTime"));
        String taskToDoTime = cursor.getString(cursor.getColumnIndex("taskToDoTime"));
        int taskType = cursor.getInt(cursor.getColumnIndex("taskType"));
        int taskDelay = cursor.getInt(cursor.getColumnIndex("taskDelay"));
        int taskLevel = cursor.getInt(cursor.getColumnIndex("taskLevel"));
        String picture = cursor.getString(cursor.getColumnIndex("picture"));
        int taskWeight = cursor.getInt(cursor.getColumnIndex("taskWeight"));
        cursor.close();
        return new Task(taskType, taskDelay, taskLevel, picture, name,
                                   taskCreateTime, taskToDoTime, describe, taskWeight);
    }

    /**
     * 存储单个的任务
     *
     * @param task
     */
    public void saveTask(Task task) {
        dbManageHelper.saveFile(task.getName(),
                                           task.getDescribe(),
                                           task.getTaskCreateTime().getTime(),
                                           task.getTaskToDoTime().getTime(),
                                           task.getTaskType(), task.getTaskDelay(),
                                           task.getTaskLevel(), task.getPicture());
    }

    /**
     * 修改任务
     *
     * @param task
     * @param taskEditName
     */
    public void modifyItem(Task task, String taskEditName) {
        dbManageHelper.modifyItem(task.getName(), task.getDescribe(),
                                             task.getTaskCreateTime().getTime(),
                                             task.getTaskToDoTime().getTime(),
                                             task.getTaskType(),
                                             task.getTaskDelay(), task.getTaskLevel(),
                                             task.getPicture(), taskEditName);
    }

    /**
     * 删除任务
     *
     * @param task
     */
    public void deleteItem(Task task) {
        dbManageHelper.deleteItem(task.getName(),
                                             task.getDescribe(),
                                             task.getTaskCreateTime().getTime(),
                                             task.getTaskToDoTime().getTime(),
                                             task.getTaskType(), task.getTaskDelay(),
                                             task.getTaskLevel(), task.getPicture());
    }

    public void close()
    {
        dbManageHelper.close();
    }
}
