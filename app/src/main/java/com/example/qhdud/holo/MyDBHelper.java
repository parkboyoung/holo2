package com.example.qhdud.holo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qhdud on 2018-06-02.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context)
    {
        super(context,"holo.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "create table holo(diaryHour char(40) UNIQE primary key, content varchar(500));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
