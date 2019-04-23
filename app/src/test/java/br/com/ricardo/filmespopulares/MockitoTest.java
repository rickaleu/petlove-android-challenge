package br.com.ricardo.filmespopulares;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import javax.security.auth.callback.Callback;

import br.com.ricardo.filmespopulares.model.FilmInteractorImpl;
import br.com.ricardo.filmespopulares.model.api.FilmService;
import br.com.ricardo.filmespopulares.view.MovieDetail;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    public MovieDetail details;

    @Mock
    private static FilmInteractorImpl filmInteractorImpl;

    @Before
    public void setUpTest(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void DetailsTitleTest(){

        Mockito.when(details.getSupportActionBar().getTitle()).thenReturn("Teste de Preenchimento de Título");

    }

    @Test
    public final void testService(){

        if(filmInteractorImpl == null){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("www.google.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            filmInteractorImpl = (FilmInteractorImpl) retrofit.create(FilmService.class);

            Mockito.verify(filmInteractorImpl);
        }
    }

}
