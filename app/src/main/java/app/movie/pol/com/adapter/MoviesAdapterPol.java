package app.movie.pol.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.movie.pol.com.R;
import app.movie.pol.com.model.Movie;
import com.squareup.picasso.Picasso;

/**
 * Created by Gino Osahon on 14/03/2017.
 */
public class MoviesAdapterPol extends RecyclerView.Adapter<MoviesAdapterPol.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";

    public MoviesAdapterPol(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView rating;
        ImageView movieImage;
        TextView votes;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layoutPol);
            movieImage = (ImageView) v.findViewById(R.id.movie_imagePol);
            movieTitle = (TextView) v.findViewById(R.id.titlePol);
            rating = (TextView) v.findViewById(R.id.ratingPol);
            votes = (TextView) v.findViewById(R.id.votesPol);
        }
    }


    @Override
    public MoviesAdapterPol.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.votes.setText(movies.get(position).getVoteCount().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
