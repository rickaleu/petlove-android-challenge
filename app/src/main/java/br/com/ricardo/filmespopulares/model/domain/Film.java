package br.com.ricardo.filmespopulares.model.domain;

import java.io.Serializable;

public class Film implements Serializable {

    private String rate;
    private String title;
    private String posterPath;
    private String language;
    private String originalTitle;
    private String backdropPath;
    private String overview;
    private String releaseDate;

    public Film(String rate, String title, String posterPath, String language, String originalTitle, String backdropPath, String overview, String releaseDate) {
        this.rate = rate;
        this.title = title;
        this.posterPath = posterPath;
        this.language = language;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
