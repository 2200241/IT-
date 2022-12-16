package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.favorite.Favorite
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepository @Inject constructor(application: Application) {

    private val favoriteDao = RecipeAppDatabase.getDatabase(application).favoriteDao()

    //全件取得
    fun getAllFavorites() = favoriteDao.getAllFavorites()

    //追加
    suspend fun addFavorite(favorite: Favorite) {
        withContext(Dispatchers.IO){
            favoriteDao.addFavorite(favorite)
        }
    }

    //指定したIDを削除
    suspend fun deleteFavorite(favorite_id: Int){
        withContext(Dispatchers.IO) {
            favoriteDao.deleteFavorite(favorite_id)
        }
    }

    //更新
    suspend fun updateFavorite(favorite: Favorite){
        withContext(Dispatchers.IO) {
            favoriteDao.updateFavorite(favorite)
        }
    }

    //全件削除
    suspend fun deleteAllFavorites(){
        withContext(Dispatchers.IO) {
            favoriteDao.deleteAllFavorites()
        }
    }
}

