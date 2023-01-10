package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.favorite_menu.FavoriteMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteMenuRepository @Inject constructor(application: Application) {

    private val favoriteMenuDao = RecipeAppDatabase.getDatabase(application).favoriteMenuDao()

    //全件取得
    fun getAllMenus() = favoriteMenuDao.getAllFavoriteMenus()

    //追加
    suspend fun addFavoriteMenu(favoriteMenu: FavoriteMenu) {
        withContext(Dispatchers.IO){
            favoriteMenuDao.addFavoriteMenu(favoriteMenu)
        }
    }

    //指定したIDを削除
    suspend fun deleteFavoriteMenu(id: Int){
        withContext(Dispatchers.IO) {
            favoriteMenuDao.deleteFavoriteMenu(id)
        }
    }

    //更新
    suspend fun updateFavoriteMenu(favoriteMenu: FavoriteMenu){
        withContext(Dispatchers.IO) {
            favoriteMenuDao.updateFavoriteMenu(favoriteMenu)
        }
    }

    //全件削除
    suspend fun deleteAllMenus(){
        withContext(Dispatchers.IO) {
            favoriteMenuDao.deleteAllFavoriteMenus()
        }
    }
}

