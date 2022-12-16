package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.menu.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class MenuRepository @Inject constructor(application: Application) {

    private val menuDao = RecipeAppDatabase.getDatabase(application).menuDao()

    //全件取得
    fun getAllMenus() = menuDao.getAllMenus()

    //追加
    suspend fun addMenu(menu: Menu) {
        withContext(Dispatchers.IO){
            menuDao.addMenu(menu)
        }
    }

    //指定したIDを削除
    suspend fun deleteMenu(menu_id: Int){
        withContext(Dispatchers.IO) {
            menuDao.deleteMenu(menu_id)
        }
    }

    //更新
    suspend fun updateMenu(menu: Menu){
        withContext(Dispatchers.IO) {
            menuDao.updateMenu(menu)
        }
    }

    //全件削除
    suspend fun deleteAllMenus(){
        withContext(Dispatchers.IO) {
            menuDao.deleteAllMenus()
        }
    }
}

