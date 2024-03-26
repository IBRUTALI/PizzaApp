package com.ighorosipov.pizzaapp.data.mapper

import com.ighorosipov.pizzaapp.data.model.entity.MealEntity

class NetworkMealMapper {

    fun mapMealToData(meal: com.ighorosipov.pizzaapp.data.model.Meal): MealEntity {
        return MealEntity(
            id = meal.id,
            title = meal.title,
            thumbnail = meal.thumbnail
        )
    }

}