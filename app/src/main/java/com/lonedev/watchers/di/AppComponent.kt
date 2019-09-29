package com.lonedev.watchers.di

import android.app.Application
import com.lonedev.watchers.base.BaseApplication
import com.lonedev.watchers.di.builders.ActivityBuildersModule
import com.lonedev.watchers.di.builders.FragmentBuildersModule
import com.lonedev.watchers.di.modules.AppModule
import com.lonedev.watchers.di.builders.ViewModelFactoryModule
import com.lonedev.watchers.di.builders.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}