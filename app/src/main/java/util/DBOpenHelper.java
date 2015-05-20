package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015-05-19.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "create table myTask(name text not null,describe text,taskCreateTime text,taskToDoTime text,taskType integer,taskDelay integer,taskLevel integer,picture text,taskWeight integer)";

    Context context;
    public DBOpenHelper(Context context) {

        super(context, "myTask", null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
