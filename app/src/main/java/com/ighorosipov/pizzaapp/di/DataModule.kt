package com.ighorosipov.pizzaapp.di

import android.content.Context
import com.ighorosipov.pizzaapp.data.dto.room.MealsDao
import com.ighorosipov.pizzaapp.data.dto.room.MealsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    companion object {

        @Singleton
        @Provides
        fun provideMarketDatabase(context: Context): MealsDatabase {
            return MealsDatabase.getDB(context)
        }

        @Singleton
        @Provides
        fun provideMarketDao(mealsDatabase: MealsDatabase): MealsDao {
            return mealsDatabase.mealsDao
        }

    }

}