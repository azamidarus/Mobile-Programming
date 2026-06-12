package com.example.a211409_drnazatulaini_project1

data class GroceryItem(
    val id: Int,
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