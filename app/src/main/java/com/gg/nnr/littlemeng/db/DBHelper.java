package com.gg.nnr.littlemeng.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gg.nnr.littlemeng.constant.Constant;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "newsItem.db";
    public static final String TABLE_NAME = "Item";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        //CursorFactory设置为null,使用默认值
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用,add:1,已添加
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, itemName VARCHAR, addOrDel TEXT )");
        ContentValues cv = new ContentValues();
        for (int i = 0; i < Constant.items.length; i++) {
            cv.put("itemName", Constant.items[i]);
            if (i < 5) {
                cv.put("addOrDel", "1");
            } else {
                cv.put("addOrDel", "0");
            }
            db.insert(TABLE_NAME, null, cv);
        }

    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
