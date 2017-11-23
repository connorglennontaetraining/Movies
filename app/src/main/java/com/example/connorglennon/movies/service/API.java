package com.example.connorglennon.movies.service;

/**
 * Created by Connor Glennon on 23/11/2017.
 */

public class API {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String BASE_URL_IMAGES = "https://image.tmdb.org/t/p/w500";
    public static final String MOVIE_DB = "movie/";
    public static final String PARAM_TOP_RATED= "top_rated";
    public static final String PARAM_ID = "id";
    public static final String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";
    public static final String QUERY_API_KEY = "api_key";

    public static final String REQUEST_TOP_RATED_MOVIES = BASE_URL + MOVIE_DB  + PARAM_TOP_RATED;
    public static final String REQUEST_TOP_RATED_MOVIE_DETAILS = BASE_URL + MOVIE_DB + "{" + PARAM_ID + "}";
}
