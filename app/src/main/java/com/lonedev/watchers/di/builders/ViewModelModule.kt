package com.lonedev.watchers.di.builders

import androidx.lifecycle.ViewModel
import com.lonedev.watchers.di.util.ViewModelKey
import com.lonedev.watchers.ui.MainViewModel
import com.lonedev.watchers.ui.fragment.DetailMovieViewModel
import com.lonedev.watchers.ui.fragment.favourite.FavouriteViewModel
import com.lonedev.watchers.ui.fragment.popular.PopularViewModel
import com.lonedev.watchers.ui.fragment.toprated.TopRatedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(movieViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(popularViewModel: PopularViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopRatedViewModel::class)
    abstract fun binTopRatedViewModel(topRatedViewModel: TopRatedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    abstract fun binFavouriteViewModel(favouriteViewModel: FavouriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMovieViewModel::class)
    abstract fun binDetailMovieViewModel(detailMovieViewModel: DetailMovieViewModel): ViewModel
}
