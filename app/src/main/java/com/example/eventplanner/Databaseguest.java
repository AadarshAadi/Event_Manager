package com.example.eventplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class Databaseguest extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 3; // Incremented version
    private static final String TABLE_GUEST = "guest";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone_no";
    private static final String COLUMN_EMAIL_USER = "user_email"; // Changed to avoid duplicate column name

    public Databaseguest(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GUEST_TABLE = "CREATE TABLE " + TABLE_GUEST + "("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY,"
                + COLUMN_PHONE + " INT, "
                + COLUMN_EMAIL_USER + " TEXT" + ")";
        db.execSQL(CREATE_GUEST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUEST);
        onCreate(db);
    }

    public void addGuest(String email1, long phone, String user_attached) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email1);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_EMAIL_USER, user_attached);
        long hu = db.insert(TABLE_GUEST, null, values);
        System.out.println("Rows affected=" + hu);
        db.close();
    }
    public List<String> showguestlist(String useremail) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> guestList = new ArrayList<>();
        String query = "SELECT " + COLUMN_EMAIL + ", " + COLUMN_PHONE + " FROM " + TABLE_GUEST + " WHERE " + COLUMN_EMAIL_USER + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{useremail});

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
                long phone = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
                guestList.add("Email: " + email + ", Phone: " + phone);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return guestList;
    }
    public void deleteguest(String mail)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        int ip=db.delete(TABLE_GUEST,COLUMN_EMAIL +"=?",new String[]{mail});
        db.close();
    }
    public void eraseguest()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_GUEST);
        db.close();
    }
}
