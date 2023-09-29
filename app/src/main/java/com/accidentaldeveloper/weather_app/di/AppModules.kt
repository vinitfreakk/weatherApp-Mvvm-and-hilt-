package com.accidentaldeveloper.weather_app.di

import com.accidentaldeveloper.weather_app.api.ApiServices
import com.accidentaldeveloper.weather_app.helper.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    fun provideBaseUrl():String = AppConstant.BASE_URL

    @Provides
    fun provideRetorfitInstance():ApiServices = Retrofit
        .Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiServices::class.java)

}