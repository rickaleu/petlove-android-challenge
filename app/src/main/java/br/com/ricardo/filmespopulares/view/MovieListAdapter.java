package br.com.ricardo.filmespopulares.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.response.ResponseFilms;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342";

    private List<ResponseFilms> movieLists;

    public MovieListAdapter(List<ResponseFilms> movieLists) {
        this.movieLists = movieLists;
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_film, viewGroup, false);

        MovieListViewHolder holder = new MovieListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder movieListViewHolder, int i) {

        Picasso.with(movieListViewHolder.imageMoviePoster.getContext())
                .load(IMAGE_BASE_URL + movieLists.get(i).getPoster_path())
                .into(movieListViewHolder.imageMoviePoster);

        movieListViewHolder.textMovieTitle.setText(movieLists.get(i).getOriginal_title());
        movieListViewHolder.textMovieVoteAverage.setText(movieLists.get(i).getVote_average());
        movieListViewHolder.textMovieReleaseDate.setText(movieLists.get(i).getRelease_date());
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageMoviePoster;
        private TextView textMovieTitle;
        private TextView textMovieVoteAverage;
        private TextView textMovieReleaseDate;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMoviePoster = (ImageView) itemView.findViewById(R.id.item_movie_poster);
            textMovieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
            textMovieVoteAverage = (TextView) itemView.findViewById(R.id.item_movie_vote);
            textMovieReleaseDate = (TextView) itemView.findViewById(R.id.item_movie_release_date);



        }
    }
}
