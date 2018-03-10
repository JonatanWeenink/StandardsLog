package nl.jonatanweenink.standardslog;

import android.provider.BaseColumns;

/**
 * Created by Laptop Weenink on 5-3-2018.
 */

public final class StandardContract {
    private StandardContract() {}
   /* Inner class that defines the table contents */
    public static class StandardEntry implements BaseColumns {
        // Labels Table Columns names
        public static final String TABLE_NAME = "Standards";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_ARTIST = "artist";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_NOTES = "notes";
        public static final String COLUMN_NAME_KEY = "key";
    }
}
