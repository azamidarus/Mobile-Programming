package com.example.a211409_drnazatulaini_lab5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryItem::class], version = 1, exportSchema = false)
abstract class NgamNgamDatabase : RoomDatabase() {
    abstract fun groceryDao(): GroceryDao

    companion object {
        @Volatile
        private var Instance: NgamNgamDatabase? = null

        fun getDatabase(context: Context): NgamNgamDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NgamNgamDatabase::class.java, "ngamngam_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}