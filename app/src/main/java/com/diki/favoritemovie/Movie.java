package com.diki.favoritemovie;

public class Movie {
    private int id;
    private String poster;
    private String title;
    private String overview;
    private Genre[] genres;
    private String releaseDate;
    private double rating;
    private String tagline;
    private String language;
    private int duration;

    public Movie(int id, String poster, String title, String overview, Genre[] genres, String releaseDate, double rating, String tagline, String language, int duration) {
        this.id = id;
        this.poster = poster;
        this.title = title;
        this.overview = overview;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.tagline = tagline;
        this.language = language;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public String getTagline() {
        return tagline;
    }

    public String getLanguage() {
        return language;
    }

    public int getDuration() {
        return duration;
    }
}
