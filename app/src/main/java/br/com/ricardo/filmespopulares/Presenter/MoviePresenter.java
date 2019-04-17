package br.com.ricardo.filmespopulares.Presenter;

import br.com.ricardo.filmespopulares.view.MovieView;

public interface MoviePresenter {

    void attachView(MovieView view);
    void detachView();
    void requestPopularMovies();

}
