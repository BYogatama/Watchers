/*
 * Created by Bagus Yogatama on 6/29/19 2:23 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 6/29/19 2:23 PM
 */

package com.lonedev.watchers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lonedev.watchers.data.local.converters.IntTypeConverter
import com.lonedev.watchers.data.local.converters.StringTypeConverter
import com.lonedev.watchers.data.local.dao.FavouriteDao
import com.lonedev.watchers.data.model.MovieResult

@Database(
    entities = [MovieResult::class],
    version = 1, exportSchema = false
)
@TypeConverters(IntTypeConverter::class, StringTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): FavouriteDao
}