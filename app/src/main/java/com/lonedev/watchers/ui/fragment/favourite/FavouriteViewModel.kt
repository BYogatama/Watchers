package com.lonedev.watchers.ui.fragment.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lonedev.watchers.base.BaseViewModel
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.repo.FavouriteRepository
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val repository: FavouriteRepository
) : BaseViewModel() {

    private val _favouriteMovies: MutableLiveData<List<MovieResult>> by lazy {
        MutableLiveData<List<MovieResult>>()
    }

    val favouriteMovies: LiveData<List<MovieResult>> = _favouriteMovies

    fun getFavouriteMovies() {
        repository.getFavourites()
            .subscribe(favouriteMoviObserver())
    }


    private fun favouriteMoviObserver(): DisposableObserver<List<MovieResult>> {
        return object : DisposableObserver<List<MovieResult>>() {
            override fun onComplete() {
                this.dispose()
            }

            override fun onNext(movies: List<MovieResult>) {
                _favouriteMovies.postValue(movies)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }

}