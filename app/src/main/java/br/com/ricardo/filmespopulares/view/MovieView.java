package br.com.ricardo.filmespopulares.view;

import br.com.ricardo.filmespopulares.model.response.ResponseFilm;

public interface MovieView {

    void showLoading();
    void hideLoading();
    void showData(ResponseFilm item);
    void showError();

}
