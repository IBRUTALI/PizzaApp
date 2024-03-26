package com.ighorosipov.pizzaapp.data.mapper

import com.ighorosipov.pizzaapp.data.model.entity.MealEntity
import com.ighorosipov.pizzaapp.domain.model.Meal

class MealMapper {

    fun mapListOfMealToDomain(meals: List<MealEntity>): List<Meal> =
        meals.map { meal ->
            mapDataToDomain(meal)
        }

    fun mapDomainToData(meal: Meal): MealEntity {
        return MealEntity(
            id = meal.id,
            title = meal.title,
            thumbnail = meal.thumbnail
        )
    }

    fun mapDataToDomain(meal: MealEntity): Meal {
        return Meal(
            id = meal.id,
            title = meal.title,
            thumbnail = meal.thumbnail
        )
    }

}