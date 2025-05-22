package com.example.flavorfinder

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredients")
    fun getAllIngredients(): LiveData<List<Ingredient>>

    @Insert
    suspend fun insert(ingredient: Ingredient)  //

    @Query("DELETE FROM ingredients")
    suspend fun clearAll()


}


