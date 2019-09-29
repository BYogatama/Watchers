package com.lonedev.watchers.ui.fragment.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lonedev.watchers.base.BaseViewModel
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.repo.MovieRepository
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _popularMovies: MutableLiveData<MovieResult> by lazy {
        MutableLiveData<MovieResult>()
    }

    val popularMovies: LiveData<MovieResult> = _popularMovies

    fun getPopularMovies(page: Int) {
        movieRepository.getPopularMovies(page)
            .subscribe(popularMovieObserver())
    }


    private fun popularMovieObserver(): DisposableObserver<MovieResult> {
        return object : DisposableObserver<MovieResult>() {
            override fun onComplete() {
                this.dispose()
            }

            override fun onNext(movie: MovieResult) {
                _popularMovies.postValue(movie)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }


}