package com.lonedev.watchers.di.builders

import androidx.lifecycle.ViewModelProvider
import com.lonedev.watchers.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelFactory): ViewModelProvider.Factory

}