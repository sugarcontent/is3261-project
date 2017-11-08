package com.example.chuak.projectapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by chuak on 7/11/2017.
 */

public class NotesDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NOTES = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTES = "NotesTable";

    // Column fields in database
    private static final String KEY_ID = "id";
    private static final String VIDEO_NUM = "video";
    private static final String VIDEO_DESC = "description";

    private static final String CREATE_TABLE_NOTES = "CREATE TABLE "
            + TABLE_NOTES + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + VIDEO_NUM + " INT, "
            + VIDEO_DESC + " TEXT);";


    public NotesDatabaseHelper(Context context) {
        super(context, DATABASE_NOTES, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTES); // create question table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_NOTES); // drop table if exists
        onCreate(db);
    }

    public void insertEntry(String text, int vidNum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VIDEO_NUM, vidNum);
        values.put(VIDEO_DESC, text);
        db.insert(TABLE_NOTES, null, values);

        db.close();
    }
    
    public void toastEntries(Context ctx) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NOTES, null);
        c.moveToFirst();
        do {
            Toast.makeText(ctx, c.getString(c.getColumnIndex(VIDEO_DESC)), Toast.LENGTH_SHORT).show();
        } while (c.moveToNext());

        c.close();
    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES);
    }

    // Function to get all entries of the particular video number
    /*
    public String getDescription(int videoNum) {

        String query = "SELECT description FROM " + TABLE_NOTES
                + "WHERE video = " + videoNum;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        String description = c.getString(c.getColumnIndex(VIDEO_DESC));
        c.close();

        return description;
    } */

    /*
    public void updateDescription(String newDesc, int videoNum) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(VIDEO_DESC, newDesc);
        int success = db.update(TABLE_NOTES, value, "video="+videoNum, null);

        if (success != 1) {
           System.out.println("ERROR IN UPDATE");
        } else {
            System.out.println("UPDATE SUCCESS!");
        }
    }
    */
}
