/*
 * Created by Bagus Yogatama on 6/29/19 2:25 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 6/29/19 2:25 PM
 */

package com.lonedev.watchers.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.lonedev.watchers.data.model.MovieResult
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM movies")
    fun getAllFavourite(): Observable<List<MovieResult>>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    fun getFavourite(movieId: Int): Observable<MovieResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavourite(movie: MovieResult) : Completable

    @Delete
    fun deleteFavourite(movie: MovieResult) : Completable
}