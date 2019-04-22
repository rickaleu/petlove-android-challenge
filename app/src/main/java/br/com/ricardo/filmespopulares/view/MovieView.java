package br.com.ricardo.filmespopulares.view;

import br.com.ricardo.filmespopulares.model.response.ResponseFilm;
import br.com.ricardo.filmespopulares.model.response.ResultFilms;

public interface MovieView {

    void addNewItemMovie(ResponseFilm item);
    void showLoading();
    void hideLoading();
    void showError();

}
