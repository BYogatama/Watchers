package com.lonedev.watchers.di.modules

import android.app.Application
import android.content.Context
import com.lonedev.watchers.di.builders.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, ViewModelModule::class, DatabaseModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(application: Application): Context {
        return application.applicationContext
    }
}