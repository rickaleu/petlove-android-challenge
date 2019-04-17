package br.com.ricardo.filmespopulares.model;

import br.com.ricardo.filmespopulares.model.api.FilmService;
import br.com.ricardo.filmespopulares.model.response.ResultFilms;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmInteractorImpl implements FilmInteractor {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(FilmService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    FilmService service = retrofit.create(FilmService.class);

    @Override
    public Call<ResultFilms> requestRetrofitAccess() {

        //Método da interface FilmService, que é onde faz a chamada completa da API.
        return service.getPopularFilms("b70848b875278d63417beecbdddc4841");
    }

}
