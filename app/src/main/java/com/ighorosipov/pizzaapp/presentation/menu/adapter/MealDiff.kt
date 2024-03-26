package com.ighorosipov.pizzaapp.presentation.menu.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ighorosipov.pizzaapp.domain.model.Meal

class MealDiff(
    private val oldList: List<Meal>,
    private val newList: List<Meal>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }

            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }

            oldList[oldItemPosition].thumbnail != newList[newItemPosition].thumbnail -> {
                false
            }

            else -> true
        }
    }
}