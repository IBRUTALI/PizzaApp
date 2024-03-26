package com.ighorosipov.pizzaapp.data.dto.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ighorosipov.pizzaapp.data.model.entity.MealEntity

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(item: MealEntity)

    @Query("SELECT * FROM meal")
    suspend fun getMeals(): List<MealEntity>

    //delete

    //update

}