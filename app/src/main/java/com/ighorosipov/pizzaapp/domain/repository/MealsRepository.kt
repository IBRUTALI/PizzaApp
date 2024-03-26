package com.ighorosipov.pizzaapp.domain.repository

import com.ighorosipov.pizzaapp.domain.model.Meal
import com.ighorosipov.pizzaapp.utils.Result

interface MealsRepository {

    suspend fun insertMeal(meal: Meal)

    suspend fun getMeals(): Result<List<Meal>>
}