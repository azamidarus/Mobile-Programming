package com.example.a211409_drnazatulaini_project1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NgamNgamViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NgamNgamState())
    val uiState: StateFlow<NgamNgamState> = _uiState.asStateFlow()

    private var itemIdCounter = 0

    fun addIngredient(name: String, amount: Float, unit: String, isExtra: Boolean = false) {
        _uiState.update { currentState ->
            val currentList = currentState.groceryList.toMutableList()
            val existingItemIndex = currentList.indexOfFirst { it.name.equals(name, ignoreCase = true) }

            if (existingItemIndex != -1) {
                val existingItem = currentList[existingItemIndex]
                currentList[existingItemIndex] = existingItem.copy(amount = existingItem.amount + amount)
            } else {
                currentList.add(GroceryItem(itemIdCounter++, name, amount, unit, false, isExtra))
            }

            val newTotalMeals = if (!isExtra) currentState.totalMealsPlanned + 1 else currentState.totalMealsPlanned

            currentState.copy(groceryList = currentList, totalMealsPlanned = newTotalMeals)
        }
    }

    fun toggleItemBought(itemId: Int) {
        _uiState.update { currentState ->
            val updatedList = currentState.groceryList.map { item ->
                if (item.id == itemId) item.copy(isBought = !item.isBought) else item
            }
            currentState.copy(groceryList = updatedList)
        }
    }
}