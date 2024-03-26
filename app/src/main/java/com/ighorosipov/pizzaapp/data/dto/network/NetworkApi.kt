package com.ighorosipov.pizzaapp.data.dto.network

import com.ighorosipov.pizzaapp.data.model.Meals
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("api/json/v1/1/filter.php?a=Italian")
    suspend fun getMeals(): Response<Meals>

    companion object {
        const val BASE_URL = "https://themealdb.com/"
    }

}