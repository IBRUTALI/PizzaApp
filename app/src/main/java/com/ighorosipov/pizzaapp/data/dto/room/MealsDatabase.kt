package com.ighorosipov.pizzaapp.data.dto.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ighorosipov.pizzaapp.data.model.entity.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MealsDatabase : RoomDatabase() {

    abstract val mealsDao: MealsDao

    companion object {

        fun getDB(context: Context): MealsDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MealsDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        private const val DATABASE_NAME = "meals_db"

    }

}