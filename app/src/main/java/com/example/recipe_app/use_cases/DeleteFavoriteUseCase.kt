package com.example.recipe_app.use_cases

import com.example.recipe_app.repositories.FavoriteRepository
import com.example.recipe_app.room.favorite.Favorite
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend fun deleteFavorite(id: Int) {

        repository.deleteFavorite(id)
    }
}
