package com.example.recipe_app.room.shoppingitem

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuWithRecipeThumbDao{
    @Transaction
    @Query("SELECT * FROM ShoppingItems")
    fun getAllShoppingItems(): Flow<List<MenuWithRecipeThumb>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addShoppingItem(menuWithRecipeThumb: MenuWithRecipeThumb)

    @Query("delete from ShoppingItems where id = :id")
    suspend fun deleteShoppingItem(id: Int)

    @Update
    suspend fun updateShoppingItem(menuWithRecipeThumb: MenuWithRecipeThumb)

    @Query("delete from ShoppingItems")
    suspend fun deleteAllShoppingItems()

}