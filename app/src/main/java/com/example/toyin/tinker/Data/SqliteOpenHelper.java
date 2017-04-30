package com.example.toyin.tinker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Toyin on 09/04/2017.
 */
public class SqliteOpenHelper extends SQLiteOpenHelper {
    public static final String Database_name = "Tinker.db";
    public static int Database_version = 1;
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Database_name + "("
            + Tinker.TinkerEntry.ID + " INTEGER PRIMARY KEY," + Tinker.TinkerEntry.Column_name + " TEXT NOT NULL,"
            + Tinker.TinkerEntry.Column_score + " INT);";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE " + Tinker.TinkerEntry.TABLE_NAME;

    public SqliteOpenHelper(Context context){
        super(context, Database_name, null, Database_version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int old_version, int new_version){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}

