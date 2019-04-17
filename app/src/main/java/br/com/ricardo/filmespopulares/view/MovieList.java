package br.com.ricardo.filmespopulares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.filmespopulares.Presenter.MoviePresenter;
import br.com.ricardo.filmespopulares.Presenter.MoviePresenterImpl;
import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.FilmInteractor;
import br.com.ricardo.filmespopulares.model.FilmInteractorImpl;
import br.com.ricardo.filmespopulares.model.response.ResponseFilms;

public class MovieList extends AppCompatActivity implements MovieView{

    private Toolbar toolbarMovieList;
    private RecyclerView recyclerViewMovieList;

    private MoviePresenter moviePresenter;

    private List<ResponseFilms> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        toolbarMovieList = (Toolbar) findViewById(R.id.toolbar_movielist);
        setSupportActionBar(toolbarMovieList);

        //Instanciando a View nesta activity que assumiu o papel de "Impl" da parte do View no MVP.
        //Passando apenas a view para a MoviePresenterImpl
        moviePresenter = new MoviePresenterImpl(this);

        movieList = new ArrayList<>();
        recyclerViewMovieList = (RecyclerView) findViewById(R.id.recycler_movielist);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(MovieList.this, 2);
        recyclerViewMovieList.setLayoutManager(gridLayoutManager);

    }


    @Override
    public void showData(ResponseFilms item) {

        movieList.add(item);
        recyclerViewMovieList.setAdapter(new MovieListAdapter(movieList));
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Erro ao obter a lista de filmes.", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        moviePresenter.requestPopularMovies();
    }

    @Override
    protected void onStop() {
        super.onStop();

        moviePresenter.detachView();
    }
}