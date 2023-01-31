package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.RecipeWithoutCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMenuDao {

    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image FROM menus " +
            "JOIN recipes ON recipes.id IN menus.recipe_ids " +
            "WHERE menus.id IN (SELECT id FROM favorite_menu_ids)")
    fun getAllFavoriteMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteMenu(id: Int)

    @Query("delete from favorite_menu_ids where id = :id")
    suspend fun deleteFavoriteMenu(id: Int)

    @Update
    suspend fun updateFavoriteMenu(id: Int)

    @Query("delete from favorite_menu_ids")
    suspend fun deleteAllFavoriteMenus()
}