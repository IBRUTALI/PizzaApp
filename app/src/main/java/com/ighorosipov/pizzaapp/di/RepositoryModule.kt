package com.ighorosipov.pizzaapp.di

import com.ighorosipov.pizzaapp.data.repository.MealsRepositoryImpl
import com.ighorosipov.pizzaapp.data.repository.NetworkRepositoryImpl
import com.ighorosipov.pizzaapp.domain.repository.MealsRepository
import com.ighorosipov.pizzaapp.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindMealsRepository(mealsRepository: MealsRepositoryImpl): MealsRepository

    @Singleton
    @Binds
    fun bindNetworkRepository(networkRepository: NetworkRepositoryImpl): NetworkRepository

}