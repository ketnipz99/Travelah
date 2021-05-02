package com.example.travelah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;



public class UserDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "user_profile";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                                " (" + COLUMN_USERNAME + " TEXT, " +
                                COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                                COLUMN_PASSWORD + " TEXT); ";

        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public Boolean insertData (String username, String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = myDB.insert("user_profile", null, contentValues);

        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }

    //Check for user duplication before signing up
    public Boolean checkRedundancy (String email) {
        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery("SELECT * FROM user_profile WHERE email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Authenticate user upon loggin in
    public Boolean authenticateUser (String email, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM user_profile WHERE email = ? " +
                "AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //Query user's name for displaying
    public Cursor fetchUsername (String email) {

        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor cursor = myDB.rawQuery("SELECT username FROM user_profile WHERE email = ?", new String[]{email});

        return cursor;

    }

    //Update user password
    public Boolean updatePassword(String email, String newPassword) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);

        long result = myDB.update("user_profile", contentValues, "email = ?", new String[] {email});

        if(result == 1)
            return true;
        else
            return false;
    }


    //Update username
    public Boolean updateUsername (String email, String newUsername) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", newUsername);

        long result = myDB.update("user_profile", contentValues, "email = ?", new String[] {email});

        if(result == 1)
            return true;
        else
            return false;
    }

    //Update username
    public Boolean deleteUser (String email) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        long result = myDB.delete("user_profile", "email = ?",  new String[] {email});


        if(result == 1)
            return true;
        else
            return false;
    }


}
