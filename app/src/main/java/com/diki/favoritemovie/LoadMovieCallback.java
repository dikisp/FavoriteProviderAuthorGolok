package com.diki.favoritemovie;

import android.database.Cursor;

interface LoadMovieCallback {
    void postExecute(Cursor notes);
}
