package br.com.ricardo.filmespopulares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.response.ResponseFilm;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_FILM = "EXTRA_FILM";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    private final String backDrop = "w780";
    private final String thumb = "w154";

    private FrameLayout frameContainerMovieDetail;
    private ProgressBar progressBarMovieDetail;
    private Toolbar toolbarMovieDetail;
    private ImageView imageMovieDetail;
    private ImageView imageMovieThumb;
    private TextView textMovideDetailOriginalName;
    private TextView textMovideDetailLanguage;
    private TextView textMovideDetailDate;
    private TextView textMovideDetailRate;
    private TextView textMovideDetailOverview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        toolbarMovieDetail = (Toolbar) findViewById(R.id.toolbar_movie_detail);
        setSupportActionBar(toolbarMovieDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        frameContainerMovieDetail = (FrameLayout) findViewById(R.id.frame_container_movie_detail);
        progressBarMovieDetail = (ProgressBar) findViewById(R.id.progressBar_movie_detail);
        imageMovieDetail = (ImageView) findViewById(R.id.image_movie_detail);
        imageMovieThumb = (ImageView) findViewById(R.id.image_movie_detail_thumb);
        textMovideDetailOriginalName = (TextView) findViewById(R.id.text_movie_detail_original_name);
        textMovideDetailLanguage = (TextView) findViewById(R.id.text_movie_detail_language);
        textMovideDetailDate = (TextView) findViewById(R.id.text_movie_detail_release);
        textMovideDetailRate = (TextView) findViewById(R.id.text_movie_detail_rate);
        textMovideDetailOverview = (TextView) findViewById(R.id.text_movie_detail_overview);

        frameContainerMovieDetail.setVisibility(View.VISIBLE);
        progressBarMovieDetail.setVisibility(View.VISIBLE);


        ResponseFilm film = (ResponseFilm) getIntent().getSerializableExtra(EXTRA_FILM);

        if(film != null){

            frameContainerMovieDetail.setVisibility(View.GONE);
            progressBarMovieDetail.setVisibility(View.GONE);

            getSupportActionBar().setTitle(film.getTitle());

            Picasso.with(this)
                    .load(IMAGE_BASE_URL + backDrop + film.getBackdropPath())
                    .into(this.imageMovieDetail);

            Picasso.with(this)
                    .load(IMAGE_BASE_URL + thumb + film.getPosterPath())
                    .into(this.imageMovieThumb);

            textMovideDetailOriginalName.setText(film.getOriginalTitle());
            textMovideDetailLanguage.setText(film.getLanguage());
            textMovideDetailDate.setText(film.getReleaseDate());
            textMovideDetailRate.setText(film.getRate());
            textMovideDetailOverview.setText(film.getOverview());

        }else {
            Toast.makeText(this, getString(R.string.error_movie_detail), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
