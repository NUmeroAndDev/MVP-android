package com.numero.mvp_example

import android.app.Application
import com.numero.mvp_example.di.ApiModule
import com.numero.mvp_example.di.ApplicationComponent
import com.numero.mvp_example.di.DaggerApplicationComponent

class MvpExampleApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .apiModule(ApiModule())
                .build()
    }
}