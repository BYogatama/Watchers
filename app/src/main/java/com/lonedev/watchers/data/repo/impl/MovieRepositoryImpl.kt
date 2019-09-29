package com.lonedev.watchers.data.repo.impl

import com.lonedev.moviecatalogue.base.shceduler.SchedulerProvider
import com.lonedev.watchers.BuildConfig
import com.lonedev.watchers.data.local.dao.FavouriteDao
import com.lonedev.watchers.data.model.*
import com.lonedev.watchers.data.repo.MovieRepository
import com.lonedev.watchers.data.rest.NetworkService
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

internal class MovieRepositoryImpl constructor(
    private val service: NetworkService,
    private val schedulerProvider: SchedulerProvider
) : MovieRepository {

    override fun getPopularMovies(page: Int): Observable<MovieResult> {
        return this.service
            .getPopularMovies(BuildConfig.API_KEY, getLocale(), page)
            .subscribeOn(this.schedulerProvider.io())
            .retryWhen { it.flatMap { Observable.timer(100, TimeUnit.MILLISECONDS) } }
            .map(Movies::results)
            .flatMapIterable { movie -> movie }
            .flatMap {
                getMovieGenres(it)
            }
    }
    override fun getTopRatedMovies(page: Int): Observable<MovieResult> {
        return this.service
            .getTopRatedMovies(BuildConfig.API_KEY, getLocale(), page)
            .subscribeOn(this.schedulerProvider.io())
            .retryWhen { it.flatMap { Observable.timer(150, TimeUnit.MILLISECONDS) } }
            .map(Movies::results)
            .flatMapIterable { movie -> movie }
            .flatMap {
                getMovieGenres(it)
            }
    }

    override fun getMovieGenres(movie: MovieResult): Observable<MovieResult> {
        return this.service
            .getMovieGenres(BuildConfig.API_KEY, getLocale())
            .subscribeOn(this.schedulerProvider.io())
            .retryWhen { it.flatMap { Observable.timer(150, TimeUnit.MILLISECONDS) } }
            .map(Genres::genres)
            .flatMapIterable { genre -> genre }
            .filter { movie.genreIds.contains(it.id) }
            .toList()
            .map { genres ->
                val genre = genres.joinToString(separator = ", ") { it.name }
                movie.genres = genre
                return@map movie
            }
            .toObservable()
            .share()
            .cache()

    }

    override fun getMovieVideos(movie: MovieResult): Observable<List<VideoResult>> {
        return this.service
            .getMovieVideos(movie.id, BuildConfig.API_KEY, getLocale())
            .subscribeOn(this.schedulerProvider.io())
            .map(Videos::results)
    }

    private fun getLocale(): String {
        var language = Locale.getDefault().toString()
        language = language.replace("_", "-")

        if (language == "in-ID") {
            language = "id-ID"
        }

        return language
    }

}