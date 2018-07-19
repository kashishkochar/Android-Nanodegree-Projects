package com.example.kochar.inventory_app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kochar on 7/5/2018.
 */

public class ProductDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "productor.db";
    private static final int DATABASE_VERSION = 1;

    public ProductDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_QUERY = "CREATE TABLE " + ProductContractor.ProductEntry.TABLE_NAME
                + "(" + ProductContractor.ProductEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ProductContractor.ProductEntry.NAME_OF_PRODUCT + " TEXT NOT NULL, "
                +ProductContractor.ProductEntry.QUANTITY_OF_PRODUCT + " INTEGER NOT NULL, "
                +ProductContractor.ProductEntry.COST_OF_PRODUCT + " INTEGER NOT NULL, "
                +ProductContractor.ProductEntry.IMAGE + " BLOB); ";

        db.execSQL(SQL_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
