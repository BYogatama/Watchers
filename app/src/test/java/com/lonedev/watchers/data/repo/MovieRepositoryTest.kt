package com.lonedev.watchers.data.repo

import com.lonedev.watchers.BuildConfig
import com.lonedev.watchers.base.shceduler.TrampolineSchedulerProvider
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.repo.impl.MovieRepositoryImpl
import com.lonedev.watchers.data.rest.NetworkService
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepositoryTest {

    lateinit var repository: MovieRepository


    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(NetworkService::class.java)

        repository = MovieRepositoryImpl(service, TrampolineSchedulerProvider())
    }

    @Test
    fun getPopularMovies() {
        repository.getPopularMovies(1)
            .test()
            .assertComplete()
    }

    @Test
    fun getTopRatedMovies() {
        repository.getTopRatedMovies(1)
            .test()
            .assertComplete()
    }

    @Test
    fun getMovieGenres() {
        var movies = MovieResult(genreIds = listOf(28))
        repository.getMovieGenres(movies)
            .map { movie -> movie.genres }
            .test()
            .assertValue("Action")
            .assertComplete()

        movies = MovieResult(genreIds = listOf(28, 12, 16))
        repository.getMovieGenres(movies)
            .map { movie -> movie.genres }
            .test()
            .assertValue("Action, Adventure, Animation")
            .assertComplete()
    }

    @Test
    fun getMovieVideos() {
        val movies = MovieResult(id = 19201)
        repository.getMovieVideos(movies)
            .test()
            .assertComplete()
    }
}