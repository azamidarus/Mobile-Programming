package com.example.a211409_drnazatulaini_lab5

import android.app.Application

class NgamNgamApplication : Application() {
    lateinit var repository: GroceryRepository

    override fun onCreate() {
        super.onCreate()
        val database = NgamNgamDatabase.getDatabase(this)
        repository = GroceryRepository(database.groceryDao())
    }
}