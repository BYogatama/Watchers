package com.lonedev.watchers.di.modules

import com.lonedev.moviecatalogue.base.shceduler.SchedulerProvider
import com.lonedev.watchers.BuildConfig
import com.lonedev.watchers.base.shceduler.MainSchedulerProvider
import com.lonedev.watchers.data.local.dao.FavouriteDao
import com.lonedev.watchers.data.repo.FavouriteRepository
import com.lonedev.watchers.data.repo.MovieRepository
import com.lonedev.watchers.data.repo.impl.FavouriteRepositoryImpl
import com.lonedev.watchers.data.repo.impl.MovieRepositoryImpl
import com.lonedev.watchers.data.rest.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }else{
            OkHttpClient()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider {
        return MainSchedulerProvider()
    }


    @Provides
    @Singleton
    fun provideMovieRepository(service: NetworkService, scheduler: SchedulerProvider): MovieRepository {
        return MovieRepositoryImpl(service, scheduler)
    }


}