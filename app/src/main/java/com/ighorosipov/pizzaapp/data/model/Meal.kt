package com.ighorosipov.pizzaapp.data.model

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val title: String,
    @SerializedName("strMealThumb") val thumbnail: String,
)