package br.com.ricardo.filmespopulares.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.ricardo.filmespopulares.R;
import br.com.ricardo.filmespopulares.model.Film;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private List<Film> filmList;

    public MovieListAdapter(List<Film> filmList) {
        this.filmList = filmList;
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

        movieListViewHolder.movieTitle.setText(filmList.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder{

        private TextView movieTitle;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);

        }
    }
}
