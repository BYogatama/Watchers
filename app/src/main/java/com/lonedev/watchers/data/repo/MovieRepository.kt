package com.lonedev.watchers.data.repo

import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.model.VideoResult
import io.reactivex.Observable

interface MovieRepository {
    fun getPopularMovies(page: Int): Observable<MovieResult>
    fun getTopRatedMovies(page: Int): Observable<MovieResult>
    fun getMovieGenres(movie : MovieResult): Observable<MovieResult>
    fun getMovieVideos(movie : MovieResult): Observable<List<VideoResult>>
}