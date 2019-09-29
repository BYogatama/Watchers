package com.lonedev.watchers.data.repo

import com.lonedev.watchers.data.model.MovieResult
import io.reactivex.Completable
import io.reactivex.Observable

interface FavouriteRepository {
    fun getFavourites() : Observable<List<MovieResult>>
    fun saveFavourite(movie: MovieResult) : Completable
    fun deleteFavourite(movie: MovieResult) : Completable
}