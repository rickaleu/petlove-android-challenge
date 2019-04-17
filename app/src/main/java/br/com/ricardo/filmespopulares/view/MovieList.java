package br.com.ricardo.filmespopulares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import br.com.ricardo.filmespopulares.R;

public class MovieList extends AppCompatActivity {

    private Toolbar toolbarMovieList;
    private RecyclerView recyclerViewMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        toolbarMovieList = (Toolbar) findViewById(R.id.toolbar_movielist);
        setSupportActionBar(toolbarMovieList);

        recyclerViewMovieList = (RecyclerView) findViewById(R.id.recycler_movielist);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(MovieList.this);
        recyclerViewMovieList.setLayoutManager(linearLayoutManager);

        MovieListAdapter adapter = new MovieListAdapter();
        recyclerViewMovieList.setAdapter(adapter);


    }
}
