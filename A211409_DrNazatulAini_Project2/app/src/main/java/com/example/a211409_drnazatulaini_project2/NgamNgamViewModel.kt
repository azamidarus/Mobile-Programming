package com.example.a211409_drnazatulaini_project2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CommunityItem(
    val name: String = "",
    val amount: Float = 0f,
    val unit: String = ""
)

class NgamNgamViewModel(private val repository: GroceryRepository) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _communityList = MutableStateFlow<List<CommunityItem>>(emptyList())
    val communityList: StateFlow<List<CommunityItem>> = _communityList

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

    fun scanAndAddProduct(barcode: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getProductByBarcode(barcode)
                val productName = response.product?.product_name

                if (!productName.isNullOrEmpty()) {
                    addIngredient(productName, 1f, "unit", isExtra = false)
                } else {
                    addIngredient("Unknown: $barcode", 1f, "unit", isExtra = false)
                }
            } catch (e: Exception) {
                addIngredient("Scanned: $barcode", 1f, "unit", isExtra = false)
            }
        }
    }

    fun toggleItemBought(item: GroceryItem) {
        viewModelScope.launch {
            repository.updateItem(item.copy(isBought = !item.isBought))
        }
    }

    fun donateToCommunity(item: GroceryItem) {
        val donation = hashMapOf(
            "name" to item.name,
            "amount" to item.amount,
            "unit" to item.unit
        )
        db.collection("donations").add(donation)
    }

    fun fetchCommunityItems() {
        db.collection("donations").get().addOnSuccessListener { result ->
            val items = result.mapNotNull { document ->
                document.toObject(CommunityItem::class.java)
            }
            _communityList.value = items
        }.addOnFailureListener {
            Log.e("Firebase", "Error fetching data", it)
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
