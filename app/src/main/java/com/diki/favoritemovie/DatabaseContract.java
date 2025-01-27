package com.diki.favoritemovie;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {
    public static final String AUTHORITY = "com.diki.submisisatu";
    private static final String SCHEME = "content";

    private DatabaseContract() {
    }

    public static final class MovieColumns implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String MOVIE_ID = "movie_id";
        public static final String ID = "id";
        public static final String TITLE = "name";
        public static final String POSTER = "posterPath";
        public static final String OVERVIEW = "description";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }
}