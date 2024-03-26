package com.ighorosipov.pizzaapp.domain.repository

import com.ighorosipov.pizzaapp.data.model.Meals
import com.ighorosipov.pizzaapp.utils.Result

interface NetworkRepository {

    suspend fun getMeals(): Result<Meals>

}