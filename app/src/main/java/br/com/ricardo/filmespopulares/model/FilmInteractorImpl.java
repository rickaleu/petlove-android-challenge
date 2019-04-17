package br.com.ricardo.filmespopulares.model;

import br.com.ricardo.filmespopulares.model.api.FilmService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmInteractorImpl implements FilmInteractor {

    FilmService service;

    @Override
    public FilmService getRetrofitInstance() {

        if(service == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FilmService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(FilmService.class);

        }

        return service;
    }
}
