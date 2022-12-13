package com.example.recipe_app.use_cases

import android.util.Log
import com.example.recipe_app.repositories.FavoriteRepository
import com.example.recipe_app.room.favorite.Favorite
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository){

    private var favoriteList: List<Favorite> = emptyList()

    @Throws(InvalidGetFavoritesException::class)
    suspend fun getFavorites(): List<Favorite> {

        favoriteList = favoriteRepository.getAllFavorites()

        if (favoriteList.isEmpty()) {
            throw InvalidGetFavoritesException("favoriteList is empty")
        }
        if (favoriteList.first().name.isBlank()) {
            throw InvalidGetFavoritesException("お気に入りがありません")
        }

        return favoriteList
    }

    // サンプルデータ
    suspend fun setTestFavoriteData(): List<Favorite> {
        favoriteRepository.deleteAllFavorites()
        favoriteRepository.addFavorite(Favorite(0,1,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,2,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,3,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,4,"test","test"))

        return favoriteRepository.getAllFavorites()
    }
}

class InvalidGetFavoritesException(message: String): Exception(message)

