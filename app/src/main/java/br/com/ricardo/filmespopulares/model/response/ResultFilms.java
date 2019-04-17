package br.com.ricardo.filmespopulares.model.response;

import java.util.List;

public class ResultFilms {

    private List<ResponseFilms> results;

    public ResultFilms(List<ResponseFilms> results) {
        this.results = results;
    }

    public List<ResponseFilms> getResults() {
        return results;
    }

    public void setResults(List<ResponseFilms> results) {
        this.results = results;
    }
}
