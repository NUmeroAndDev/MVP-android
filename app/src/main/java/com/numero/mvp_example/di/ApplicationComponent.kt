package com.numero.mvp_example.di

import android.app.Application
import com.numero.mvp_example.MvpExampleApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApiModule::class),
    (ActivityModule::class),
    (RepositoryModule::class)
])
interface ApplicationComponent : AndroidInjector<MvpExampleApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: Application)

}