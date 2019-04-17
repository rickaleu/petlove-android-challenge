package br.com.ricardo.filmespopulares.model.response;

public class ResponseFilms{

    private String poster_path;
    private String original_title;


    public ResponseFilms(String poster_path, String original_title) {
        this.poster_path = poster_path;
        this.original_title = original_title;
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
}
