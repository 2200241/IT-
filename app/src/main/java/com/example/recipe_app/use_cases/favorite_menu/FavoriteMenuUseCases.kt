package com.example.recipe_app.use_cases.favorite_menu

import com.example.recipe_app.data.MenuWithoutIngredients
import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.example.recipe_app.room.favorite_menu.FavoriteMenu
import javax.inject.Inject

class FavoriteMenuUseCases @Inject constructor(
    private val favoriteMenuRepository: FavoriteMenuRepository
) {
    fun getFavoriteMenus() = favoriteMenuRepository.getAllFavoriteMenus()
    suspend fun addFavoriteMenu(menu: MenuWithoutIngredients) {
        favoriteMenuRepository.addFavoriteMenu(
            FavoriteMenu(
                id = menu.id,
                recipes = menu.recipes
            )
        )
    }
    suspend fun deleteFavoriteMenu(id: Int) = favoriteMenuRepository.deleteFavoriteMenu(id)
}

