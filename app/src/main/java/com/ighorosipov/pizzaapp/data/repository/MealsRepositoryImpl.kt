package com.ighorosipov.pizzaapp.data.repository

import com.ighorosipov.pizzaapp.data.dto.room.MealsDao
import com.ighorosipov.pizzaapp.data.mapper.MealMapper
import com.ighorosipov.pizzaapp.data.mapper.NetworkMealMapper
import com.ighorosipov.pizzaapp.domain.model.Meal
import com.ighorosipov.pizzaapp.domain.repository.MealsRepository
import com.ighorosipov.pizzaapp.domain.repository.NetworkRepository
import com.ighorosipov.pizzaapp.utils.Result
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val dao: MealsDao
): MealsRepository{

    override suspend fun insertMeal(meal: Meal) {
        dao.insertMeal(MealMapper().mapDomainToData(meal))
    }

    override suspend fun getMeals(): Result<List<Meal>> {
        return try {
            val getFromNetwork = networkRepository.getMeals()
            if (getFromNetwork.data != null) {
                getFromNetwork.data.meals.forEach { meal ->
                    val mealEntity = NetworkMealMapper().mapMealToData(meal)
                    dao.insertMeal(mealEntity)
                }
            }
            Result.Success(
                data = MealMapper().mapListOfMealToDomain(dao.getMeals())
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(message = e.message ?: "An unknown error occurred.")
        }
    }

}