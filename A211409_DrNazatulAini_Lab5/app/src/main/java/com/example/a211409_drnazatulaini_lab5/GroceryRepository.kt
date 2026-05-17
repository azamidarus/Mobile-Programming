package com.example.a211409_drnazatulaini_lab5

import kotlinx.coroutines.flow.Flow

class GroceryRepository(private val groceryDao: GroceryDao) {
    fun getAllItemsStream(): Flow<List<GroceryItem>> = groceryDao.getAllItems()

    suspend fun insertItem(item: GroceryItem) = groceryDao.insert(item)

    suspend fun updateItem(item: GroceryItem) = groceryDao.update(item)

    suspend fun getItemByName(name: String): GroceryItem? = groceryDao.getItemByName(name)
}