package br.com.ricardo.filmespopulares.model.pojo;

import java.util.List;

public class ResultFilms {

    private List<ResponseFilm> results;

    public ResultFilms(List<ResponseFilm> results) {
        this.results = results;
    }

    public List<ResponseFilm> getResults() {
        return results;
    }

    public void setResults(List<ResponseFilm> results) {
        this.results = results;
    }
}