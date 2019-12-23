package com.example.emotion_n_music;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MovieDbHelper extends SQLiteOpenHelper {


    public static final String LOG_TAG = MovieDbHelper.class.getSimpleName();


    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MOVIIIE.db";


    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =  "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ("
                + MovieContract.MovieEntry.COLUMN_ID + " TEXT NOT NULL, "
                + MovieContract.MovieEntry.COLUMN_KEY + " TEXT "+
                  ");";


        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






}
