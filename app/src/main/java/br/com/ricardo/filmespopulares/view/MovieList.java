package br.com.ricardo.filmespopulares.view;

import android.content.Intent;
import android.os.Handler;
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
import br.com.ricardo.filmespopulares.model.response.ResponseFilm;

public class MovieList extends AppCompatActivity implements MovieView {

    private Toolbar toolbarMovieList;
    private FrameLayout frameContainerMovieList;
    private ProgressBar progressBarMovieList;
    private RecyclerView recyclerViewMovieList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MovieListAdapter adapter;

    private MoviePresenter moviePresenter;

    private List<ResponseFilm> movieList;

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

        frameContainerMovieList = (FrameLayout) findViewById(R.id.frame_container_movielist);
        progressBarMovieList = (ProgressBar) findViewById(R.id.progressBar_movielist);
        showLoading();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container_movielist);

        recyclerViewMovieList = (RecyclerView) findViewById(R.id.recycler_movielist);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(MovieList.this, 2);
        recyclerViewMovieList.setLayoutManager(gridLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {

                        adapter.clear();
                        moviePresenter.requestPopularMovies();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
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
    public void showData(ResponseFilm item) {

        hideLoading();

        movieList.add(item);

        adapter = new MovieListAdapter(movieList);
        recyclerViewMovieList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new MovieListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(MovieList.this, MovieDetail.class);

                ResponseFilm film = movieList.get(position);

                intent.putExtra(MovieDetail.EXTRA_FILM, film);
                startActivity(intent);


            }
        });
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_movielist), Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        showLoading();
        moviePresenter.requestPopularMovies();
    }

    @Override
    protected void onStop() {
        super.onStop();

        moviePresenter.detachView();
    }

}
