package com.example.recipe_app.use_cases.favorite_menu

import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import javax.inject.Inject

class DeleteFavoriteMenuUseCase @Inject constructor(
    private val repository: FavoriteMenuRepository
) {
    suspend fun deleteFavorite(id: Int) {

        repository.deleteFavoriteMenu(id)
    }
}
