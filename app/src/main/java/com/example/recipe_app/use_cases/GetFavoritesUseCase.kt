package com.example.recipe_app.use_cases

import android.util.Log
import com.example.recipe_app.repositories.FavoriteRepository
import com.example.recipe_app.room.favorite.Favorite
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository){

    private var favoriteList: List<Favorite> = arrayListOf()

    @Throws(InvalidGetFavoritesException::class)
    suspend fun getFavorites(){
        favoriteList = favoriteRepository.getAllFavorite()
        if (favoriteList == null) {
            throw InvalidGetFavoritesException("favoriteList is null")
        }
        if (favoriteList.first().name.isBlank()) {
            throw InvalidGetFavoritesException("お気に入りがありません")
        }
    }

    // サンプルデータ
    suspend fun setTestFavoriteData() {
        favoriteRepository.deleteAllFavorites()
        favoriteRepository.addFavorite(Favorite(0,1,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,2,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,3,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,4,"test","test"))
        Log.d("test","${favoriteRepository.getAllFavorite().toString()}")

    }
}


class InvalidGetFavoritesException(message: String): Exception(message)

