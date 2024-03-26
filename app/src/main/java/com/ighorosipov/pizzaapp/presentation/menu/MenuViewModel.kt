package com.ighorosipov.pizzaapp.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ighorosipov.pizzaapp.domain.model.Meal
import com.ighorosipov.pizzaapp.domain.repository.MealsRepository
import com.ighorosipov.pizzaapp.presentation.menu.adapter.Category
import com.ighorosipov.pizzaapp.utils.Result
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel @AssistedInject constructor(
    private val repository: MealsRepository
) : ViewModel() {

    private val _city = MutableLiveData("Москва")
    val city: LiveData<String> = _city

    private val _category = MutableLiveData<Category>()
    val category: LiveData<Category> = _category

    private val _meals = MutableLiveData<Result<List<Meal>>>()
    val meals: LiveData<Result<List<Meal>>> = _meals

    init {
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            _meals.postValue(Result.Loading())
            val meals = repository.getMeals()
            _meals.postValue(meals)
        }
    }

    fun changeCategory(category: Category) {
        _category.value = category
    }

    fun changeCity(city: String) {
        _city.value = city
    }

    @AssistedFactory
    interface Factory {

        fun create(): MenuViewModel

    }
}