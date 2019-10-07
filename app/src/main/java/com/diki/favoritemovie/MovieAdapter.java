package com.diki.favoritemovie;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMovies;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void setMovies(ArrayList<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.poster_movie)
        SimpleDraweeView sdvPoster;

        @BindView(R.id.tv_movie_title)
        TextView tvTitle;
        @BindView(R.id.tv_movie_description)
        TextView tvDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            System.out.println("https://image.tmdb.org/t/p/w185" + movie.getPoster());
            sdvPoster.setImageURI("https://image.tmdb.org/t/p/w185" + movie.getPoster());
            tvDescription.setText(movie.getOverview());
        }
    }
}
