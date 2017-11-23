package com.example.connorglennon.movies.service;

import com.example.connorglennon.movies.model.Page;
import com.example.connorglennon.movies.model.movie.details.MovieDetails;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Connor Glennon on 23/11/2017.
 */

public interface ApiRequest {
    @GET(API.REQUEST_TOP_RATED_MOVIES)
    public Observable<Page> getTopRatedMovies(@Query(API.QUERY_API_KEY) String apiKey);

    @GET(API.REQUEST_TOP_RATED_MOVIE_DETAILS)
    public Observable<MovieDetails> getMovie(@Path(API.PARAM_ID) int id, @Query(API.QUERY_API_KEY) String apiKey);
}
