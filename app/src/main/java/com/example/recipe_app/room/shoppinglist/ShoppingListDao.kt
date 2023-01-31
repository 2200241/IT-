package com.example.recipe_app.room.shoppinglist

import androidx.room.*
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeThumb
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao{

    @Query("SELECT * FROM ShoppingLists")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>

    @Query("SELECT MAX(id) FROM ShoppingLists")
    fun getShoppingListMaxId(): Flow<Int>

    @Query("insert into ShoppingLists(recipes) values(:recipes)")
    suspend fun addShoppingList(recipes:List<RecipeThumb>)

    @Query("delete from ShoppingLists where id = :id")
    suspend fun deleteShoppingList(id: Int)

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList)

    @Query("delete from ShoppingLists")
    suspend fun deleteAllShoppingLists()

}