package nl.jonatanweenink.standardslog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Laptop Weenink on 5-3-2018.
 */

    public class DataSource {
        private SQLiteDatabase mDatabase;
        private final DBHelper mDBHelper;
        private final String[] STANDARDS_ALL_COLUMNS_ALL_COLUMNS = {
                StandardContract.StandardEntry.COLUMN_NAME_ID,
                StandardContract.StandardEntry.COLUMN_NAME_TITLE,
                StandardContract.StandardEntry.COLUMN_NAME_ARTIST,
                StandardContract.StandardEntry.COLUMN_NAME_DATE,
                StandardContract.StandardEntry.COLUMN_NAME_STATUS,
                StandardContract.StandardEntry.COLUMN_NAME_NOTES,
                StandardContract.StandardEntry.COLUMN_NAME_KEY };
        // Opens the mDatabase to use it
        public void open()  {
            mDatabase = mDBHelper.getWritableDatabase();
        }
        // Closes the mDatabase when you no longer need it
        public void close() {
            mDBHelper.close();
        }

        public DataSource(Context context) {
            mDBHelper = new DBHelper(context);
        }
        /**
         * Save an object within the mDatabase.
         *
         * @param standard the object to be saved.
         */
        public void save(Standard standard) {
            ContentValues values = new ContentValues();
            values.put(StandardContract.StandardEntry.COLUMN_NAME_TITLE, standard.getTitle());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_ARTIST, standard.getArtist());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_DATE, standard.getDateAdded());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_STATUS, standard.getStatus());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_NOTES, standard.getNotes());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_KEY, standard.getKey());
            // Inserting Row
            mDatabase.insert(StandardContract.StandardEntry.TABLE_NAME, null, values);
            mDatabase.close();
        }
        /**
         * Update a single entity within the mDatabase.
         *
         * @param id   the id of the entity to be updated.
         * @param standard holds the new values which will overwrite the old values.
         */
        public void update(int id, Standard standard) {
            ContentValues values = new ContentValues();
            values.put(StandardContract.StandardEntry.COLUMN_NAME_TITLE, standard.getTitle());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_ARTIST, standard.getArtist());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_DATE, standard.getDateAdded());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_STATUS, standard.getStatus());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_NOTES, standard.getNotes());
            values.put(StandardContract.StandardEntry.COLUMN_NAME_KEY, standard.getKey());

            mDatabase.update(StandardContract.StandardEntry.TABLE_NAME, values, StandardContract.StandardEntry.COLUMN_NAME_ID + "= ?", new String[]{String.valueOf(id)});
            mDatabase.close(); // Closing mDatabase connection
        }
        /**
         * Finds all game objects.
         *
         * @return a cursor holding the game objects.
         */
        public Cursor findAll() {
            return mDatabase.query(StandardContract.StandardEntry.TABLE_NAME, STANDARDS_ALL_COLUMNS_ALL_COLUMNS, null, null, null, null, null);
        }
        /**
         * Delete a single entity from the mDatabase.
         *
         * @param id the id of the entity to be deleted.
         */
        public void delete(int id) {
            mDatabase.delete(StandardContract.StandardEntry.TABLE_NAME, StandardContract.StandardEntry.COLUMN_NAME_ID + " =?",
                    new String[]{Integer.toString(id)});
        }
    }


