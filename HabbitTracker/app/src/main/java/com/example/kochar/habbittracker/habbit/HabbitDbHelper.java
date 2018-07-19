package com.example.kochar.habbittracker.habbit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kochar on 6/23/2018.
 */

public class HabbitDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "H_DataBase";
    public static final int DATABASE_VERSION = 1;

    public HabbitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HABBIT_TABLE = "CREATE TABLE " + HabbitContract.HabbitEntry.TABLE_NAME + " ("
                + HabbitContract.HabbitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabbitContract.HabbitEntry.HABBIT_NAME + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.monday + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.tuesday + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.wednesday + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.thursday + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.friday + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.saturday + "TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.sunday + "TEXT NOT NULL, );";

        db.execSQL(CREATE_HABBIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVerison) {
    }

    public void insertHabbit() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabbitContract.HabbitEntry.HABBIT_NAME, "DOING GYM");
        values.put(HabbitContract.HabbitEntry.monday, 1);
        values.put(HabbitContract.HabbitEntry.tuesday, 1);
        values.put(HabbitContract.HabbitEntry.wednesday, 1);
        values.put(HabbitContract.HabbitEntry.thursday, 1);
        values.put(HabbitContract.HabbitEntry.friday, 1);
        values.put(HabbitContract.HabbitEntry.saturday, 1);
        values.put(HabbitContract.HabbitEntry.sunday, 0);

        db.insert(HabbitContract.HabbitEntry.TABLE_NAME, null, values);
    }

    private Cursor onRead() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                HabbitContract.HabbitEntry.HABBIT_NAME,
                HabbitContract.HabbitEntry.monday,
                HabbitContract.HabbitEntry.tuesday,
                HabbitContract.HabbitEntry.saturday,
                HabbitContract.HabbitEntry.sunday
        };
        Cursor cursor = db.query(
                HabbitContract.HabbitEntry.TABLE_NAME,
                projection, null, null, null, null, null
        );
        return cursor;
    }

}




