package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MenuUseCase {
    fun getAllMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>
    fun getMenuDetail(id: Int): Flow<Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>>
    suspend fun addMenu(recipes: List<RecipeDetail>)
    suspend fun deleteMenu(id: Int)
    suspend fun updateMenu(menu: Menu)
    suspend fun checkShoppingItem(id: Int)
}

data class MenuUseCaseImpl @Inject constructor(
    private val menuRepository: MenuRepository
): MenuUseCase {
    override fun getAllMenus() = menuRepository.getAllMenus()
    override fun getMenuDetail(id: Int) = menuRepository.getMenuDetail(id)
    override suspend fun addMenu(recipes: List<RecipeDetail>) {
        menuRepository.addMenu(recipes)
    }
    override suspend fun deleteMenu(id: Int) = menuRepository.deleteMenu(id)
    override suspend fun updateMenu(menu: Menu) = menuRepository.updateMenu(menu)
    override suspend fun checkShoppingItem(id: Int) = menuRepository.checkShoppingItem(id)
}

