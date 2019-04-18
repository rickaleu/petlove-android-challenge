package br.com.ricardo.filmespopulares.view;

import br.com.ricardo.filmespopulares.model.pojo.ResponseFilm;

public interface MovieView {

    void showData(ResponseFilm item);
    void showError();

}
