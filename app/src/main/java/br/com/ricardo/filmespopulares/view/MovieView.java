package br.com.ricardo.filmespopulares.view;

import br.com.ricardo.filmespopulares.model.domain.Film;
import br.com.ricardo.filmespopulares.model.response.ResponseFilm;

public interface MovieView {

    void addNewItemMovie(Film item);
    void showLoading();
    void hideLoading();
    void showError();

}
