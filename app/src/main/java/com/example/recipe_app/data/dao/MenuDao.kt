package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.RecipeWithoutCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image " +
            "FROM menus JOIN recipes ON recipes.id IN menus.recipe_ids")
    fun getAllMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMenu(menu: Menu)

    @Query("delete from menus where id = :id")
    suspend fun deleteMenu(id: Int)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Query("delete from menus")
    suspend fun deleteAllMenus()
}