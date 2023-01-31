package com.example.recipe_app.use_cases.menu

import com.example.recipe_app.repositories.FavoriteMenuRepository
import javax.inject.Inject

class DeleteMenuUseCase @Inject constructor(
    private val repository: FavoriteMenuRepository
) {
    suspend fun deleteFavorite(id: Int) {

        repository.deleteFavoriteMenu(id)
    }
}
