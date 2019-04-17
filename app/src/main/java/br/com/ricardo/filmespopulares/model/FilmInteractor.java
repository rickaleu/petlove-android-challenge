package br.com.ricardo.filmespopulares.model;

import br.com.ricardo.filmespopulares.model.response.ResultFilms;
import retrofit2.Call;

public interface FilmInteractor {

   //Método para ser implementado na classe impl. Este método é o que complementa o acesso a API.
   // Por isso, seu nome: requestRetrofitAccess
   Call<ResultFilms> requestRetrofitAccess();

}
