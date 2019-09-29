package com.lonedev.watchers.data.repo.impl

import com.lonedev.moviecatalogue.base.shceduler.SchedulerProvider
import com.lonedev.watchers.data.local.dao.FavouriteDao
import com.lonedev.watchers.data.model.MovieResult
import com.lonedev.watchers.data.repo.FavouriteRepository
import io.reactivex.Completable
import io.reactivex.Observable

class FavouriteRepositoryImpl constructor(
    private val favouriteDao: FavouriteDao,
    private val scheduler: SchedulerProvider
) : FavouriteRepository {

    override fun getFavourites(): Observable<List<MovieResult>> {
        return this.favouriteDao
            .getAllFavourite()
            .subscribeOn(this.scheduler.io())
    }

    override fun saveFavourite(movie: MovieResult): Completable {
        return this.favouriteDao.saveFavourite(movie)
            .subscribeOn(this.scheduler.io())
    }

    override fun deleteFavourite(movie: MovieResult): Completable {
        return this.favouriteDao.deleteFavourite(movie)
            .subscribeOn(this.scheduler.io())
    }

}