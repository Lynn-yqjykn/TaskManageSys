package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import beans.Task;

/**
 * 存放数据库增删改查的原子操作的工具类
 */

public class DBManageHelper {

    public SQLiteDatabase db;
    public Context context;

    public DBManageHelper(Context context) {
        this.context = context;
        db = new DBOpenHelper(context).getWritableDatabase();
    }

    public Cursor getCursor() {
        return db.rawQuery("select * from myTask", null);
    }

    public void close() {
        db.close();
    }

    public void saveFile(String name, String describe, long time, long time2,
                         int taskType, int taskDelay, int taskLevel, String picture) {

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("describe", describe);
        values.put("taskCreateTime", time);
        values.put("taskToDoTime", time2);
        values.put("taskType", taskType);
        values.put("taskDelay", taskDelay);
        values.put("taskLevel", taskLevel);
        values.put("picture", picture);
        db.insert("myTask", null, values);

    }

    public Cursor getItem(String[] selection) {
        return db.rawQuery("select * from myTask where name = ?", selection);
    }

    public void modifyItem(String name, String describe, long time, long time2,
                           int taskType, int taskDelay, int taskLevel, String picture, String taskEditName) {
        db.execSQL("update myTask set name = ?,describe = ?,taskType = ?,picture = ? where name = ?"
                              , new Object[]{name, describe, taskType, picture, taskEditName});

    }

    public void deleteItem(String name, String describe, long time, long time2,
                           int taskType, int taskDelay, int taskLevel, String picture) {
        db.execSQL("delete from myTask where name = ?"
                              , new Object[]{name});

    }
}
