package com.example.eventplanner;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
public class Databaseevent extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_EVENT = "event";
    private static final String EVENT_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String HOST = "hostname";
    private static final String TICKET_PRIZE = "password";
    private static final String EMAIL_ASSOC = "email";
    private static final String GUESTLIST = "guestl";
    public Databaseevent(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
                + EVENT_NAME + " TEXT PRIMARY KEY,"
                + COLUMN_ADDRESS + " TEXT,"
                + HOST + " TEXT,"
                + TICKET_PRIZE + " INT,"
                + EMAIL_ASSOC + " TEXT,"
                + GUESTLIST + " TEXT"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        onCreate(db);
    }
    public void addEvent(String name, String address, String host1, int ttprize, String associate, String guestlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, name);
        values.put(COLUMN_ADDRESS, address);
        values.put(HOST, host1);
        values.put(TICKET_PRIZE, ttprize);
        values.put(EMAIL_ASSOC, associate);
        values.put(GUESTLIST, guestlist);
        db.insert(TABLE_EVENT, null, values);
        db.close();
    }
    public List<String> showeventlist(String useremail) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> eventList = new ArrayList<>();
        String query = "SELECT " + EVENT_NAME + ", " + COLUMN_ADDRESS + " , "+GUESTLIST + " FROM " + TABLE_EVENT + " WHERE " + EMAIL_ASSOC + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{useremail});

        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndexOrThrow(EVENT_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
                String awad =cursor.getString(cursor.getColumnIndexOrThrow(GUESTLIST));
                eventList.add("Name of event: " + email + ", Address: " + phone+",Guests:"+awad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eventList;
    }
    public void deleteEVENT(String EVENT)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        int ip=db.delete(TABLE_EVENT,EVENT_NAME +"=?",new String[]{EVENT});
        db.close();
    }
    public void eraseevent()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENT);
        db.close();
    }
}
