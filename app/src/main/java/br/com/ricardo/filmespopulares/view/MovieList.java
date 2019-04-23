package br.com.ricardo.filmespopulares.view;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.ricardo.filmespopulares.Presenter.MoviePresenter;
import br.com.ricardo.filmespopulares.Presenter.MoviePresenterImpl;
import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.FilmInteractor;
import br.com.ricardo.filmespopulares.model.FilmInteractorImpl;
import br.com.ricardo.filmespopulares.model.domain.Film;
import br.com.ricardo.filmespopulares.model.response.ResponseFilm;

public class MovieList extends AppCompatActivity implements MovieView {

    private Toolbar toolbarMovieList;
    private FrameLayout frameContainerMovieList;
    private ProgressBar progressBarMovieList;
    private RecyclerView recyclerViewMovieList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MovieListAdapter adapter;
    private List<Film> movieList;

    //Model
    private FilmInteractor filmInteractor;
    //Presenter
    private MoviePresenter moviePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        toolbarMovieList = (Toolbar) findViewById(R.id.toolbar_movielist);
        setSupportActionBar(toolbarMovieList);

        frameContainerMovieList = (FrameLayout) findViewById(R.id.frame_container_movielist);
        progressBarMovieList = (ProgressBar) findViewById(R.id.progressBar_movielist);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_movielist);

        movieList = new ArrayList<>();
        //Intanciando as referências de Model e Presenter.
        filmInteractor = new FilmInteractorImpl();
        moviePresenter = new MoviePresenterImpl(filmInteractor);


        //RecyclerView
        adapter = new MovieListAdapter(movieList);
        recyclerViewMovieList = (RecyclerView) findViewById(R.id.recycler_movielist);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(MovieList.this, 2);
        recyclerViewMovieList.setLayoutManager(gridLayoutManager);
        recyclerViewMovieList.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);
                moviePresenter.attachView(MovieList.this);
                if(movieList.size() != 0){
                    adapter.clear();
                    moviePresenter.requestPopularMovies();
                }
            }
        });

    }


    @Override
    public void addNewItemMovie(Film item) {

        movieList.add(item);
        adapter.notifyItemInserted(movieList.size());
        hideLoading();

        adapter.setOnItemClickListener(new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(MovieList.this, MovieDetail.class);

                Film film = movieList.get(position);

                intent.putExtra(MovieDetail.EXTRA_FILM, film);
                startActivity(intent);


            }
        });
    }

    @Override
    public void showLoading() {

        frameContainerMovieList.setVisibility(View.VISIBLE);
        progressBarMovieList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        frameContainerMovieList.setVisibility(View.GONE);
        progressBarMovieList.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_movielist), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        moviePresenter.attachView(this);
        if(movieList.size() == 0){
            moviePresenter.requestPopularMovies();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        moviePresenter.attachView(this);
        if(movieList.size() != 0){
            adapter.clear();
            moviePresenter.requestPopularMovies();
        }
    }

    @Override
    protected void onStop() {

        moviePresenter.detachView();
        super.onStop();
    }
}
