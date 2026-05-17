package com.example.a211409_drnazatulaini_lab5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {
    @Query("SELECT * FROM grocery_items ORDER BY id ASC")
    fun getAllItems(): Flow<List<GroceryItem>>

    @Query("SELECT * FROM grocery_items WHERE name LIKE :itemName LIMIT 1")
    suspend fun getItemByName(itemName: String): GroceryItem?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: GroceryItem)

    @Update
    suspend fun update(item: GroceryItem)
}