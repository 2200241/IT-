package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMenuDao {

/*    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image FROM menus " +
            "JOIN recipes ON recipes.id IN (SELECT menu_recipes.recipe_id FROM menu_recipes WHERE menu_recipes.menu_id = menus.id) " +
            "WHERE menus.id IN (SELECT menu_id FROM favorite_menu_ids) " +
            "ORDER BY menus.id"
    )*/
    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image FROM menus " +
            "JOIN menu_recipes ON menu_recipes.menu_id = menus.id " +
            "JOIN recipes ON recipes.id = menu_recipes.recipe_id " +
            "WHERE menus.id IN (SELECT menu_id FROM favorite_menu_ids) " +
            "ORDER BY menus.id"
    )
    fun getAllFavoriteMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>

    @Query("SELECT favorite_menu_ids.menu_id FROM favorite_menu_ids")
    fun getFavoriteMenuIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavoriteMenu(menuId: FavoriteMenuId)

    @Query("DELETE FROM favorite_menu_ids WHERE menu_id = :menuId")
    suspend fun deleteFavoriteMenu(menuId: Int)

    @Update
    suspend fun updateFavoriteMenu(menuId: FavoriteMenuId)

    @Query("DELETE FROM favorite_menu_ids")
    suspend fun deleteAllFavoriteMenus()
}