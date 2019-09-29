/*
 * Created by Bagus Yogatama on 6/29/19 9:21 PM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 6/29/19 9:21 PM
 */

package com.lonedev.watchers.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lonedev.moviecatalogue.base.shceduler.SchedulerProvider
import com.lonedev.watchers.BuildConfig
import com.lonedev.watchers.data.local.AppDatabase
import com.lonedev.watchers.data.local.dao.FavouriteDao
import com.lonedev.watchers.data.repo.FavouriteRepository
import com.lonedev.watchers.data.repo.impl.FavouriteRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.DB_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(database: AppDatabase): FavouriteDao = database.moviesDao()

    @Provides
    @Singleton
    fun provideFavouriteRepository(favouriteDao: FavouriteDao, scheduler: SchedulerProvider): FavouriteRepository {
        return FavouriteRepositoryImpl(favouriteDao, scheduler)
    }
}