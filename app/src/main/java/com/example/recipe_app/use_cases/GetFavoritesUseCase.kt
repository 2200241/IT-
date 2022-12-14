package com.example.recipe_app.use_cases

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.recipe_app.repositories.FavoriteRepository
import com.example.recipe_app.room.favorite.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository){

    @Throws(InvalidGetFavoritesException::class)
    suspend fun getFavorites(): Flow<List<Favorite>> {

        var favoriteList: Flow<List<Favorite>> = favoriteRepository.getAllFavorites()

        favoriteList.collect {
            if (it.isEmpty())
                throw InvalidGetFavoritesException("お気に入りがありません")
        }

        return favoriteList
    }

    // サンプルデータ
    suspend fun setTestFavoriteData(): Flow<List<Favorite>> {
        favoriteRepository.deleteAllFavorites()
        favoriteRepository.addFavorite(Favorite(0,1,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,2,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,3,"test","test"))
        favoriteRepository.addFavorite(Favorite(0,4,"test","test"))

        return favoriteRepository.getAllFavorites()
    }
}

class InvalidGetFavoritesException(message: String): Exception(message)

