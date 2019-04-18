package br.com.ricardo.filmespopulares.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.pojo.ResponseFilm;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_FILM = "EXTRA_FILM";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    private final String backDrop = "w780";
    private final String thumb = "w154";

    private TextView textMovieDetailTitle;
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

        textMovieDetailTitle = (TextView) findViewById(R.id.text_movie_detail_title);
        imageMovieDetail = (ImageView) findViewById(R.id.image_movie_detail);
        imageMovieThumb = (ImageView) findViewById(R.id.image_movie_detail_thumb);
        textMovideDetailOriginalName = (TextView) findViewById(R.id.text_movie_detail_original_name);
        textMovideDetailLanguage = (TextView) findViewById(R.id.text_movie_detail_language);
        textMovideDetailDate = (TextView) findViewById(R.id.text_movie_detail_release);
        textMovideDetailRate = (TextView) findViewById(R.id.text_movie_detail_rate);
        textMovideDetailOverview = (TextView) findViewById(R.id.text_movie_detail_overview);

        ResponseFilm film = (ResponseFilm) getIntent().getSerializableExtra(EXTRA_FILM);

        textMovieDetailTitle.setText(film.getTitle());

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

    }
}
