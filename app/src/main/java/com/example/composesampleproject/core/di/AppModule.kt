package com.example.composesampleproject.core.di

import com.example.composesampleproject.core.remote.ApiService
import com.example.composesampleproject.core.remote.RetrofitClient
import com.example.composesampleproject.features.home.data.HomeRepositoryImpl
import com.example.composesampleproject.features.home.domain.repository.HomeRepository
import com.example.composesampleproject.features.item_detail.data.DetailRepositoryImpl
import com.example.composesampleproject.features.item_detail.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService() : ApiService{
       return RetrofitClient().buildService(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository{
        return HomeRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideDetailRepository(apiService: ApiService):DetailRepository{
        return DetailRepositoryImpl(apiService)
    }
}