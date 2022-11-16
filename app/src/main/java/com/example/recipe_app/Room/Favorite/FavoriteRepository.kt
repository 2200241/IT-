package com.example.recipe_app.Room.Favorite

import com.example.recipe_app.Room.Favorite.Favorite
import com.example.recipe_app.Room.Favorite.FavoriteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(private val favoriteDao : FavoriteDao) {

    //全件取得
    fun getAllData() = favoriteDao.readAllData()

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

