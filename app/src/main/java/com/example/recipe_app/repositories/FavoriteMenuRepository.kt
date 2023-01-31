package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.FavoriteMenuId
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeWithoutCategory
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteMenuRepository {
    fun getAllFavoriteMenus(): Flow<Map<Int, List<RecipeWithoutCategory>>>
    fun getFavoriteMenuIds(): Flow<List<Int>>
    suspend fun addFavoriteMenu(menuId: Int)
    suspend fun deleteFavoriteMenu(menuId: Int)
}

class FavoriteMenuRepositoryImpl @Inject constructor(application: Application): FavoriteMenuRepository {

    private val favoriteMenuDao = RecipeAppDatabase.getDatabase(application).favoriteMenuDao()

    //全件取得
    override fun getAllFavoriteMenus() = favoriteMenuDao.getAllFavoriteMenus()

    override fun getFavoriteMenuIds() = favoriteMenuDao.getFavoriteMenuIds()

    //追加
    override suspend fun addFavoriteMenu(menuId: Int) {
        withContext(Dispatchers.IO){
            favoriteMenuDao.addFavoriteMenu(FavoriteMenuId(0, menuId))
        }
    }

    //指定したIDを削除
    override suspend fun deleteFavoriteMenu(menuId: Int){
        withContext(Dispatchers.IO) {
            favoriteMenuDao.deleteFavoriteMenu(menuId)
        }
    }

//    //更新
//    suspend fun updateFavoriteMenu(menuId: FavoriteMenuId){
//        withContext(Dispatchers.IO) {
//            favoriteMenuDao.updateFavoriteMenu(menuId)
//        }
//    }

//    //全件削除
//    suspend fun deleteAllMenus(){
//        withContext(Dispatchers.IO) {
//            favoriteMenuDao.deleteAllFavoriteMenus()
//        }
//    }
}

