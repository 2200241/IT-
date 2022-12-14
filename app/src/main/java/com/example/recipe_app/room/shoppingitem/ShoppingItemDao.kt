package com.example.recipe_app.room.shoppingitem

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao {
    @Transaction
    @Query("SELECT * FROM ShoppingItems")
    fun getAllShoppingItems(): Flow<List<ShoppingItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addShoppingItem(shoppingItem: ShoppingItem)

    @Query("delete from recipes where id = :id")
    suspend fun deleteShoppingItem(id: Int)

    @Update
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)

    @Query("delete from ShoppingItems")
    suspend fun deleteAllShoppingItems()

}