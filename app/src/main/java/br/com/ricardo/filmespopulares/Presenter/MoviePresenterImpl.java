package br.com.ricardo.filmespopulares.Presenter;

import android.util.Log;
import br.com.ricardo.filmespopulares.model.FilmInteractorImpl;
import br.com.ricardo.filmespopulares.model.response.ResponseFilms;
import br.com.ricardo.filmespopulares.model.response.ResultFilms;
import br.com.ricardo.filmespopulares.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MoviePresenterImpl implements MoviePresenter{

    private FilmInteractorImpl filmInteractor;
    private MovieView movieView;

    //Construtor setado pedindo apenas a view necessária pra carregar a lista. mas instanciando a classe FilmInteractorImpl
    public MoviePresenterImpl(MovieView movieView) {
        this.movieView = movieView;
        this.filmInteractor = new FilmInteractorImpl();
    }

    @Override
    public void detachView() {
        movieView = null;
    }

    //Aqui é quando a parte final da chamada da APU acontece.
    // Que é onde passamos a API Key como resto da url de requisição e
    // enfileiramos a requisição do método lá da Interface FilmService.
    @Override
    public void requestPopularMovies() {

        filmInteractor
                .getRetrofitInstance()
                .getPopularFilms("b70848b875278d63417beecbdddc4841")
                .enqueue(new Callback<ResultFilms>() {
        @Override
        public void onResponse(Call<ResultFilms> call, Response<ResultFilms> response) {

            if(!response.isSuccessful()) {
                Log.i(TAG, "Erro: " + response.code());
                movieView.showError();

            } else {

                ResultFilms resultFilms = response.body();

                if(resultFilms != null){
                    for(ResponseFilms rf : resultFilms.getResults()){

                        movieView.showData(rf);
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<ResultFilms> call, Throwable t) {
            Log.i(TAG, "Erro. Falha na chamada da API.");
            movieView.showError();
        }
    });

    }
}
