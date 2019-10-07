package com.diki.favoritemovie;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoadMovieCallback {
    private MovieAdapter mMovieAdapter;
    private DataObserver myObserver;

    @BindView(R.id.rv_movie)
    RecyclerView mRvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMovieAdapter = new MovieAdapter();
        mRvMovies.setAdapter(mMovieAdapter);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.MovieColumns.CONTENT_URI,
                true, myObserver);
        new GetData(this, this).execute();
    }

    @Override
    public void postExecute(Cursor notes) {
        ArrayList<Movie> movies = mapCursorToArrayList(notes);
        if (movies.size() > 0) {
            mMovieAdapter.setMovies(movies);
        } else {
            Toast.makeText(this, "Tidak Ada data film saat ini", Toast.LENGTH_SHORT).show();
            mMovieAdapter.setMovies(new ArrayList<Movie>());
        }
    }

    private ArrayList<Movie> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<Movie> notesList = new ArrayList<>();
        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.ID));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.TITLE));
            String overview = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.OVERVIEW));
            String poster = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.POSTER));
            notesList.add(new Movie(id, poster, title, overview, null, null, 0, null, null, 0));
        }
        return notesList;
    }

    private static class GetData extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMovieCallback> weakCallback;


        private GetData(Context context, LoadMovieCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(DatabaseContract.MovieColumns.CONTENT_URI,
                    null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }

    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new GetData(context, (MainActivity) context).execute();
        }
    }
}
