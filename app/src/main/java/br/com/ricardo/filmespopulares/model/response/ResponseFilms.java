package br.com.ricardo.filmespopulares.model.response;

public class ResponseFilms{

    private String poster_path;
    private String original_title;
    private String vote_average;
    private String release_date;


    public ResponseFilms(String poster_path, String original_title, String vote_average, String release_date) {
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
