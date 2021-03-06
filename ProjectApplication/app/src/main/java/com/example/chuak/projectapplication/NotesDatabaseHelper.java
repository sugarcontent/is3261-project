package com.example.chuak.projectapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by chuak on 7/11/2017.
 */

public class NotesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NOTES = "notes.db";
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

    public long insertEntry(String text, int vidNum) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VIDEO_NUM, vidNum);
        values.put(VIDEO_DESC, text);
        long success = db.insert(TABLE_NOTES, null, values);
        db.close();

        return success;
    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTES);
    }

    public ArrayList<String> getAllTutNotes(String tutId) {
        ArrayList<String> notesArrayList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES + " WHERE " + VIDEO_NUM+ " = " + tutId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all records and adding to the list
        if (c.moveToFirst()) {
            do {
                String notesText= c.getString(c.getColumnIndex(VIDEO_DESC));

                // adding to Notes list
                notesArrayList.add(notesText);
            } while (c.moveToNext());
        }
        return notesArrayList;
    }
}
