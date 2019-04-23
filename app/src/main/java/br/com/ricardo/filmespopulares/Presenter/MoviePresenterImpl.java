package br.com.ricardo.filmespopulares.Presenter;

import android.content.Context;
import android.util.Log;

import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.FilmInteractor;
import br.com.ricardo.filmespopulares.model.domain.Film;
import br.com.ricardo.filmespopulares.model.response.ResponseFilm;
import br.com.ricardo.filmespopulares.model.response.ResultFilms;
import br.com.ricardo.filmespopulares.utils.CheckConnection;
import br.com.ricardo.filmespopulares.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MoviePresenterImpl implements MoviePresenter{

    public static final String API_KEY = "b70848b875278d63417beecbdddc4841";

    private Film film;
    //Model
    private FilmInteractor filmInteractor;
    //View
    private MovieView movieView;

    //Construtor setando apenas a referência do Model(FilInteractor). A outra referência, a de View, vem pelo método attachView.
    public MoviePresenterImpl(FilmInteractor interactor) {
        this.filmInteractor = interactor;
    }

    //Este método attachView, servirá como a referência de View dentro do PresenterImpl.
    @Override
    public void attachView(MovieView view) {
        this.movieView = view;
    }

    @Override
    public void detachView() {
        this.movieView = null;
    }


    //Aqui é quando a parte final da chamada da APU acontece.
    // Que é onde passamos a API Key como resto da url de requisição e
    // enfileiramos a requisição do método lá da Interface FilmService.
    @Override
    public void requestPopularMovies() {

        if (!CheckConnection.verifyConnection((Context) movieView)) {

            movieView.showMessageNoConnection();

        } else {

            movieView.showLoading();

            filmInteractor.getRetrofitInstance()
                    .getPopularFilms(API_KEY)
                    .enqueue(new Callback<ResultFilms>() {
                        @Override
                        public void onResponse(Call<ResultFilms> call, Response<ResultFilms> response) {

                            if(response.body() == null) {
                                Log.i(TAG, "Erro: " + response.code());
                                movieView.showError(String.valueOf(R.string.presenter_response_null_error));

                            } else {

                                for(ResponseFilm rf : response.body().getResults()){

                                    movieView.addNewItemMovie(new Film(rf.getRate(), rf.getTitle(), rf.getPosterPath(),
                                            rf.getLanguage(), rf.getOriginalTitle(), rf.getBackdropPath(), rf.getOverview(),
                                            rf.getReleaseDate()));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultFilms> call, Throwable t) {
                            Log.i(TAG, "Erro. Falha na chamada da API.");
                            movieView.showError(String.valueOf(R.string.presenter_response_failure_error));
                        }
                    });

        }
    }
}
