package br.com.ricardo.filmespopulares.model;

import br.com.ricardo.filmespopulares.model.api.FilmService;
import br.com.ricardo.filmespopulares.model.response.ResultFilms;
import retrofit2.Call;

public interface FilmInteractor {

   FilmService getRetrofitInstance();

}
