package com.example.travelah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class TravalahDbOpenHelper extends SQLiteAssetHelper {

    //Open helper to open travelah database
    private static final String DATABASE_NAME = "travelah.db";
    private static final int DATABASE_VERSION = 1;


    public TravalahDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
