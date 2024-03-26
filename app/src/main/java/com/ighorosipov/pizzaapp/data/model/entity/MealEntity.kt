package com.ighorosipov.pizzaapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "str_meal") val title: String,
    @ColumnInfo(name = "str_meal_thumb") val thumbnail: String
)