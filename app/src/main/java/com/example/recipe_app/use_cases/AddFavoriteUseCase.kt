package com.example.recipe_app.use_cases

import com.example.recipe_app.repositories.FavoriteRepository
import com.example.recipe_app.room.favorite.Favorite
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    @Throws(InvalidFavoriteException::class)
    suspend fun addFavorite(favorite: Favorite) {
        if(favorite.recipe_id.toString().isBlank()) {
            throw InvalidFavoriteException("The recipe_id of the note can't be empty.")
        }
        if(favorite.name.isBlank()) {
            throw InvalidFavoriteException("The name of the note can't be empty.")
        }

        repository.addFavorite(favorite)
    }
}


class InvalidFavoriteException(message: String): Exception(message)