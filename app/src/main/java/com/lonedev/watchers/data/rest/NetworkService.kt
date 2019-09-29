package com.lonedev.watchers.data.rest

import com.lonedev.watchers.data.model.Genres
import com.lonedev.watchers.data.model.Movies
import com.lonedev.watchers.data.model.Videos
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<Movies>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<Movies>

    @GET("genre/movie/list")
    fun getMovieGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<Genres>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Observable<Videos>
}