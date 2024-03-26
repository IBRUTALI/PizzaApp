package com.ighorosipov.pizzaapp.di

import com.ighorosipov.pizzaapp.data.dto.network.NetworkApi
import com.ighorosipov.pizzaapp.data.dto.network.NetworkApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideMarketService(): NetworkApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create()
        }

    }

}