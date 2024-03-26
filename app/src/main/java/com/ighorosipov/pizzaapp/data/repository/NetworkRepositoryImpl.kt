package com.ighorosipov.pizzaapp.data.repository

import com.ighorosipov.pizzaapp.data.dto.network.NetworkApi
import com.ighorosipov.pizzaapp.data.model.Meals
import com.ighorosipov.pizzaapp.domain.repository.NetworkRepository
import com.ighorosipov.pizzaapp.utils.Result
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: NetworkApi
): NetworkRepository {

    override suspend fun getMeals(): Result<Meals> {
        return try {
            Result.Success(
                data = api.getMeals().body()!!
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(message = e.message ?: "An unknown error occurred.")
        }
    }

}