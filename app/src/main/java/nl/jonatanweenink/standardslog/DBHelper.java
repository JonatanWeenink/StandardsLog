package nl.jonatanweenink.standardslog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Laptop Weenink on 5-3-2018.
 */

    public class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "standard.db";
        private static final int DATABASE_VERSION = 1;
        // Creating the table
        private static final String DATABASE_CREATE =
                "CREATE TABLE " + StandardContract.StandardEntry.TABLE_NAME +
                        "(" +
                        StandardContract.StandardEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + StandardContract.StandardEntry.COLUMN_NAME_TITLE + " TEXT, "
                        + StandardContract.StandardEntry.COLUMN_NAME_ARTIST+ " TEXT, "
                        + StandardContract.StandardEntry.COLUMN_NAME_DATE + " TEXT, "
                        + StandardContract.StandardEntry.COLUMN_NAME_STATUS + " TEXT, "
                        + StandardContract.StandardEntry.COLUMN_NAME_NOTES + " TEXT, "
                        + StandardContract.StandardEntry.COLUMN_NAME_KEY + " TEXT )";
        //Constructor
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StandardContract.StandardEntry.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

