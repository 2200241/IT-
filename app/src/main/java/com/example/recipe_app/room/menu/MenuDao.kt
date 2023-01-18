package com.example.recipe_app.room.menu

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Query("SELECT * FROM Menus")
    fun getAllMenus(): Flow<List<Menu>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMenu(favoriteMenu: Menu)

    @Query("delete from Menus where id = :id")
    suspend fun deleteMenu(id: Int)

    @Update
    suspend fun updateMenu(Menu: Menu)

    @Query("delete from Menus")
    suspend fun deleteAllMenus()
}