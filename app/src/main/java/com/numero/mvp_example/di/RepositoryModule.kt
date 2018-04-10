package com.numero.mvp_example.di

import com.numero.mvp_example.api.ApiCall
import com.numero.mvp_example.repository.ApiRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideApiRepository(apiCall: ApiCall): ApiRepository {
        return ApiRepository(apiCall)
    }
}