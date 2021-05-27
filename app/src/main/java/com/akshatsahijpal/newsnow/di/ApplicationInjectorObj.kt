package com.akshatsahijpal.newsnow.di

import com.akshatsahijpal.newsnow.api.NewsApi
import com.akshatsahijpal.newsnow.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationInjectorObj {
    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNesApi(retro: Retrofit) : NewsApi =
        retro.create(NewsApi::class.java)
}