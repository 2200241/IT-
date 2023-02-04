package com.example.recipe_app.use_cases

import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.RecipeWithoutCategory
import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoriteMenuUseCase {
    fun getFavoriteMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>
    fun getFavoriteMenuIds(): Flow<List<Int>>
    suspend fun addFavoriteMenu(menuId: Int): Result<String, String>
    suspend fun deleteFavoriteMenu(menuId: Int): Result<String, String>
}

class FavoriteMenuUseCaseImpl @Inject constructor(
    private val favoriteMenuRepository: FavoriteMenuRepository
): FavoriteMenuUseCase {
    override fun getFavoriteMenus() = favoriteMenuRepository.getAllFavoriteMenus()
    override fun getFavoriteMenuIds() = favoriteMenuRepository.getFavoriteMenuIds()
    override suspend fun addFavoriteMenu(menuId: Int) = favoriteMenuRepository.addFavoriteMenu(menuId)
    override suspend fun deleteFavoriteMenu(menuId: Int) = favoriteMenuRepository.deleteFavoriteMenu(menuId)
}

