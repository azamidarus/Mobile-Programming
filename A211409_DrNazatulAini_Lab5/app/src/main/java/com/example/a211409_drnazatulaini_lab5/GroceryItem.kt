package com.example.a211409_drnazatulaini_lab5

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")
data class GroceryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val amount: Float,
    val unit: String,
    val isBought: Boolean = false,
    val isExtra: Boolean = false
)

data class NgamNgamState(
    val groceryList: List<GroceryItem> = emptyList(),
    val totalMealsPlanned: Int = 0
)