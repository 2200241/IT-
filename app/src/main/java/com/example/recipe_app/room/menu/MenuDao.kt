package com.example.recipe_app.room.menu

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Transaction
    @Query("SELECT * FROM Menus")
    fun getAllMenus(): Flow<List<Menu>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMenu(menu: Menu)

    @Query("delete from menus where id = :id")
    suspend fun deleteMenu(id: Int)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Query("delete from menus")
    suspend fun deleteAllMenus()
}