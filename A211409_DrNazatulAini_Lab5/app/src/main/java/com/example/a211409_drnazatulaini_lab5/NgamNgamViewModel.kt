package com.example.a211409_drnazatulaini_lab5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NgamNgamViewModel(private val repository: GroceryRepository) : ViewModel() {

    val uiState: StateFlow<NgamNgamState> = repository.getAllItemsStream().map { items ->
        NgamNgamState(
            groceryList = items,
            totalMealsPlanned = items.count { !it.isExtra }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = NgamNgamState()
    )

    fun addIngredient(name: String, amount: Float, unit: String, isExtra: Boolean = false) {
        viewModelScope.launch {
            val existingItem = repository.getItemByName(name)

            if (existingItem != null) {
                val updatedItem = existingItem.copy(amount = existingItem.amount + amount)
                repository.updateItem(updatedItem)
            } else {
                repository.insertItem(GroceryItem(name = name, amount = amount, unit = unit, isExtra = isExtra))
            }
        }
    }

    fun toggleItemBought(item: GroceryItem) {
        viewModelScope.launch {
            repository.updateItem(item.copy(isBought = !item.isBought))
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NgamNgamApplication)
                NgamNgamViewModel(application.repository)
            }
        }
    }
}