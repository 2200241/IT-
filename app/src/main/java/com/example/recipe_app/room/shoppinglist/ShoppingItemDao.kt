package com.example.recipe_app.room.shoppinglist

import androidx.room.*
import com.example.recipe_app.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDao{
    @Transaction
    @Query("SELECT * FROM ShoppingItems")
    fun getAllShoppingItems(): Flow<List<ShoppingItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addShoppingItem(shoppingItem: ShoppingItem)

    @Query("UPDATE ShoppingItems SET isChecked = :isChecked WHERE ShoppingListId = :shoppingListId AND id = :id")
    suspend fun isCheckShoppingItem(isChecked: Boolean, shoppingListId: Int, id: Int)

    @Query("delete from ShoppingItems where id = :id")
    suspend fun deleteShoppingItem(id: Int)

    @Update
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem)

    @Query("delete from ShoppingItems")
    suspend fun deleteAllShoppingItems()

}