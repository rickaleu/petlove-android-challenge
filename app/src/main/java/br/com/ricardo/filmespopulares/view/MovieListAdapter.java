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
import br.com.ricardo.filmespopulares.model.domain.Film;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    private List<Film> movieLists;

    //Atributo da interface.
    private static OnItemClickListener clickListener;

    //Contrutor do adapter
    public MovieListAdapter(List<Film> movieLists) {
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
                .load(IMAGE_BASE_URL + movieLists.get(i).getPosterPath())
                .into(movieListViewHolder.imageMoviePoster);

        movieListViewHolder.textMovieTitle.setText(movieLists.get(i).getTitle());
        movieListViewHolder.textMovieVoteAverage.setText(movieLists.get(i).getRate());
        movieListViewHolder.textMovieReleaseDate.setText(movieLists.get(i).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public void clear() {
        movieLists.clear();
        notifyDataSetChanged();
    }


    public class MovieListViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageMoviePoster;
        private TextView textMovieTitle;
        private TextView textMovieVoteAverage;
        private TextView textMovieReleaseDate;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageMoviePoster = (ImageView) itemView.findViewById(R.id.image_movie_detail);
            textMovieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
            textMovieVoteAverage = (TextView) itemView.findViewById(R.id.item_movie_vote);
            textMovieReleaseDate = (TextView) itemView.findViewById(R.id.item_movie_release_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(clickListener != null){
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    //Interface criada para ser chamada na MainActivity(MovieList) passando um filme no parâmetro.
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    //Método utilizado para comunicar o evento de clique de volta para a Activity
    public void setOnItemClickListener(OnItemClickListener listener){
        clickListener = listener;
    }
}
