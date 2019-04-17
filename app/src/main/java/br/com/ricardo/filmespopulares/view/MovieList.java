package br.com.ricardo.filmespopulares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

    private FilmInteractor filmInteractor;
    private MoviePresenter moviePresenter;

    private List<ResponseFilms> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        toolbarMovieList = (Toolbar) findViewById(R.id.toolbar_movielist);
        setSupportActionBar(toolbarMovieList);

        filmInteractor = new FilmInteractorImpl();
        moviePresenter = new MoviePresenterImpl(filmInteractor);
        movieList = new ArrayList<>();


    }


    @Override
    public void showData(ResponseFilms item) {

        movieList.add(item);

        recyclerViewMovieList = (RecyclerView) findViewById(R.id.recycler_movielist);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(MovieList.this);
        recyclerViewMovieList.setLayoutManager(linearLayoutManager);
        recyclerViewMovieList.setAdapter(new MovieListAdapter(movieList));

    }

    @Override
    protected void onStart() {
        super.onStart();

        moviePresenter.attachView(this);
        moviePresenter.requestPopularMovies();
    }

    @Override
    protected void onStop() {

        moviePresenter.detachView();
        super.onStop();
    }
}
