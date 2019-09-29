package com.lonedev.watchers.di.builders

import com.lonedev.watchers.ui.fragment.favourite.FavouriteFragment
import com.lonedev.watchers.ui.fragment.popular.PopularFragment
import com.lonedev.watchers.ui.fragment.toprated.TopRatedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePopularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun contributeTopRatedFragment(): TopRatedFragment

    @ContributesAndroidInjector
    abstract fun contributeFavouriteFragment(): FavouriteFragment

}