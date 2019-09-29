package com.lonedev.watchers.di.builders

import com.lonedev.watchers.ui.MainActivity
import com.lonedev.watchers.ui.fragment.DetailMovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailMovieActivity(): DetailMovieActivity
}