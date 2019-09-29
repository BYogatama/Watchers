package com.lonedev.watchers.ui.fragment.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lonedev.watchers.base.BaseViewModel
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.repo.MovieRepository
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class TopRatedViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _topRatedMovies: MutableLiveData<MovieResult> by lazy {
        MutableLiveData<MovieResult>()
    }

    val topRatedMovies: LiveData<MovieResult> = _topRatedMovies

    fun getTopRatedMovies(page: Int) {
        repository.getTopRatedMovies(page)
            .subscribe(popularMovieObserver())
    }


    private fun popularMovieObserver(): DisposableObserver<MovieResult> {
        return object : DisposableObserver<MovieResult>() {
            override fun onComplete() {
                this.dispose()
            }

            override fun onNext(movie: MovieResult) {
                _topRatedMovies.postValue(movie)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }


}