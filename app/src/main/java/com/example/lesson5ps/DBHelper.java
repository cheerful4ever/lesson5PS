package com.example.lesson5ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "song.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singer";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE Note
        // (id INTEGER PRIMARY KEY AUTOINCREMENT, note_content TEXT, rating
        // INTEGER );
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SINGER + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public void insertSong(String title, String singer, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        db.insert(TABLE_SONG, null, values);
        db.close();
    }

    public boolean isExistingSong(String title) {
        String selectQuery = "SELECT " + COLUMN_TITLE + " FROM "
                + TABLE_SONG + " WHERE " + COLUMN_TITLE + " = '"
                + title + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> notes = new ArrayList<Song>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + ","
                + COLUMN_SINGER + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS
                + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song songs = new Song(id, title, singer, year, stars);
                notes.add(songs);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public ArrayList<String> getSongContent() {
        ArrayList<String> songs = new ArrayList<String>();
        String selectQuery = "SELECT " + COLUMN_TITLE + ","
                + COLUMN_SINGER + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS
                + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                songs.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return songs;
    }
}
