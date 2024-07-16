package com.example.eventplanner;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Databaseuser extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_USERS = "users";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public Databaseuser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    @SuppressLint("Range")
    public String checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String resultEmail = null;
        Cursor cursor = db.rawQuery(
                "SELECT " + COLUMN_EMAIL + " FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?",
                new String[]{email, password}
        );
        if (cursor.moveToFirst()) {
            resultEmail = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
        }
        System.out.println(resultEmail);
        cursor.close();
        db.close();
        return resultEmail;
    }

    public void updatecreds(String email, String password,String oldemail)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL,email);
        cv.put(COLUMN_PASSWORD,password);
        DB.update(TABLE_USERS,cv,COLUMN_EMAIL+"=?",new String[]{oldemail});
        DB.close();
    }
    public List<String> showuserlist() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> guestList = new ArrayList<>();
        String query = "SELECT " + COLUMN_EMAIL + " FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
                guestList.add("Email: " + email);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return guestList;
    }
    public void deleteuser(String mail)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        int ip=db.delete(TABLE_USERS,COLUMN_EMAIL +"=?",new String[]{mail});
        db.close();
    }
    public void eraseuser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_USERS);
        db.close();
    }
}
