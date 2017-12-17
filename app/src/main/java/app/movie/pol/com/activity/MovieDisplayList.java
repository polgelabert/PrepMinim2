package app.movie.pol.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import app.movie.pol.com.R;
import app.movie.pol.com.adapter.MoviesAdapter;
import app.movie.pol.com.adapter.MoviesAdapterPol;
import app.movie.pol.com.model.Movie;
import app.movie.pol.com.model.MovieResponse;
import app.movie.pol.com.rest.MovieApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDisplayList extends AppCompatActivity {



    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;

    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "ac41eb9d4770a2186535a26107704a22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String titol = (String) getIntent().getExtras().get("title");
        //Log.d(TAG, titol);

        connectAndGetApiData(titol);

    }

    // This method create an instance of Retrofit
    // set the base url
    public void connectAndGetApiData(String titol){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        //Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);


        String queryString = titol;
        Call<MovieResponse> call = movieApiService.getSearch(queryString, API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                recyclerView.setAdapter(new MoviesAdapterPol(movies, R.layout.list_item_movie_pol, getApplicationContext()));

                Log.d(TAG, "Number of movies received: " + movies.size());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }



}
