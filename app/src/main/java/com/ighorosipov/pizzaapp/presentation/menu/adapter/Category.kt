package com.ighorosipov.pizzaapp.presentation.menu.adapter

import androidx.annotation.StringRes
import com.ighorosipov.pizzaapp.R

sealed class Category(@StringRes val resId: Int, val category: String) {
    data class Pizza(@StringRes val id: Int = R.string.pizza, val value: String = "pizza") : Category(id, value)
    data class Combo(@StringRes val id: Int = R.string.combo, val value: String = "combo") : Category(id, value)
    data class Dessert(@StringRes val id: Int = R.string.dessert, val value: String = "dessert") : Category(id, value)
    data class Drinks(@StringRes val id: Int = R.string.drinks, val value: String = "drinks") : Category(id, value)
}