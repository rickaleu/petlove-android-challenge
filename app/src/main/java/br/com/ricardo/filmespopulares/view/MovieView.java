package br.com.ricardo.filmespopulares.view;

import br.com.ricardo.filmespopulares.model.domain.Film;

public interface MovieView {

    void addNewItemMovie(Film item);
    void showLoading();
    void hideLoading();
    void showMessageNoConnection();
    void showError(String message);

}
