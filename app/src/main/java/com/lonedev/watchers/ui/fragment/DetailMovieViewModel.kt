package com.lonedev.watchers.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lonedev.watchers.base.BaseViewModel
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.model.VideoResult
import com.lonedev.watchers.data.repo.FavouriteRepository
import com.lonedev.watchers.data.repo.MovieRepository
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


class DetailMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val favouriteRepository: FavouriteRepository
) : BaseViewModel() {

    private val _favouriteMovies: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _videosMovies: MutableLiveData<List<VideoResult>> by lazy {
        MutableLiveData<List<VideoResult>>()
    }

    val favourite: LiveData<String> = _favouriteMovies

    val videosMovies: LiveData<List<VideoResult>> = _videosMovies

    fun saveFavourites(movie: MovieResult) {
        favouriteRepository.saveFavourite(movie)
            .subscribe(saveFavouriteCompletable())
    }

    fun deleteFavourite(movie: MovieResult) {
        favouriteRepository.deleteFavourite(movie)
            .subscribe(deleteFavouriteCompletable())
    }

    fun getVideos(movie: MovieResult) {
        movieRepository.getMovieVideos(movie)
            .subscribe(videosMovieObserver())
    }


    private fun videosMovieObserver(): DisposableObserver<List<VideoResult>> {
        return object : DisposableObserver<List<VideoResult>>() {
            override fun onComplete() {
                this.dispose()
            }

            override fun onNext(movie: List<VideoResult>) {
                _videosMovies.postValue(movie)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    private fun saveFavouriteCompletable(): DisposableCompletableObserver {
        return object : DisposableCompletableObserver() {
            override fun onComplete() {
                _favouriteMovies.postValue("Success Saving Favourite")
                this.dispose()
            }

            override fun onError(e: Throwable) {
                _favouriteMovies.postValue("Failed Saving Favourite")
                this.dispose()
            }

        }
    }

    private fun deleteFavouriteCompletable(): DisposableCompletableObserver {
        return object : DisposableCompletableObserver() {
            override fun onComplete() {
                _favouriteMovies.postValue("Success Delete Favourite")
                this.dispose()
            }

            override fun onError(e: Throwable) {
                _favouriteMovies.postValue("Failed Delete Favourite")
                this.dispose()
            }

        }
    }
}