package com.example.recipe_app.use_cases

import com.example.recipe_app.repositories.FavoriteRepository
import com.example.recipe_app.room.favorite.Favorite
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    @Throws(InvalidAddFavoriteException::class)
    suspend fun addFavorite(favorite: Favorite) {
        //データ削除 これは最後に消す
//        repository.deleteAllFavorites()

        //エラー処理
        if(favorite.recipe_id.toString().isBlank()) {
            throw InvalidAddFavoriteException("The recipe_id of the note can't be empty.")
        }
        if(favorite.name.isBlank()) {
            throw InvalidAddFavoriteException("The name of the note can't be empty.")
        }

        repository.addFavorite(favorite)
    }
}


class InvalidAddFavoriteException(message: String): Exception(message)
