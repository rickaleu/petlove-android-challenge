package br.com.ricardo.filmespopulares.Presenter;

import android.util.Log;

import br.com.ricardo.filmespopulares.model.FilmInteractor;
import br.com.ricardo.filmespopulares.model.response.ResponseFilms;
import br.com.ricardo.filmespopulares.model.response.ResultFilms;
import br.com.ricardo.filmespopulares.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenterImpl implements MoviePresenter{

    private MovieView movieView;
    private FilmInteractor filmInteractor;

    //Construtor dessa implementação, passando como parâmetro um atributo do tipo FilmInteractor.
    public MoviePresenterImpl(FilmInteractor interactor) {
        this.filmInteractor = interactor;
    }

    @Override
    public void attachView(MovieView view) {
        movieView = view;
    }

    @Override
    public void detachView() {
        movieView = null;
    }

    //Nesse método, de fato, a mágica acontece. Que é onde enfileiramos a requisição do método lá da Interface FilmService.
    @Override
    public void requestPopularMovies() {

     //Aqui, é a parte final de toda a requisição do Retrofit.
        filmInteractor.requestRetrofitAccess().enqueue(new Callback<ResultFilms>() {
        @Override
        public void onResponse(Call<ResultFilms> call, Response<ResultFilms> response) {

            if(!response.isSuccessful()) {
                Log.i("TAG", "Erro: " + response.code());

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
            Log.i("ERROOOOO", "Deu erro");
        }
    });

    }
}
