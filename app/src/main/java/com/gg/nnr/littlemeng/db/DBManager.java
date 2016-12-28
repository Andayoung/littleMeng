package com.gg.nnr.littlemeng.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void addItem(String itemName) {
        db.beginTransaction();  //开始事务
        db.execSQL("INSERT INTO item VALUES(null, ?, ?)", new Object[]{itemName, 1});
        db.setTransactionSuccessful();  //设置事务成功完成

    }


    public void deleteItem(String itemName) {
        db.delete(DBHelper.TABLE_NAME, "itemName= ?", new String[]{itemName});
    }

    public void updateItem(String itemName, String addOrDel) {
        ContentValues cv = new ContentValues();
        cv.put("addOrDel", addOrDel);
        db.update(DBHelper.TABLE_NAME, cv, "itemName = ?", new String[]{itemName});
    }


    public String[] query(String addOrDel) {
        Cursor c = queryTheCursor(addOrDel);
        String[] items = new String[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            String item = c.getString(c.getColumnIndex("itemName"));
            items[i] = item;
            i++;
        }
        c.close();
        return items;
    }

    public Cursor queryTheCursor(String addOrDel) {
        Cursor c = db.rawQuery("SELECT * FROM Item WHERE addOrDel =? ", new String[]{addOrDel});
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }
}
