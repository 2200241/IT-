package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.MenuRepository
import com.example.recipe_app.repositories.RecipeRepository
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MenuUseCase {
    fun getAllMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>
    fun getMenuDetail(id: Int): Flow<Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>>
    suspend fun addMenu(recipes: List<DetailedRecipe>): Result<String, String>
    suspend fun deleteMenu(id: Int, recipeIds: List<Int>): Result<String, String>
    suspend fun updateMenu(menu: Menu): Result<String, String>
    suspend fun checkShoppingItem(id: Int): Result<String, String>
}

data class MenuUseCaseImpl @Inject constructor(
    private val menuRepository: MenuRepository,
    private val recipeRepository: RecipeRepository
): MenuUseCase {
    override fun getAllMenus() = menuRepository.getAllMenus()
    override fun getMenuDetail(id: Int) = menuRepository.getMenuDetail(id)
    override suspend fun addMenu(recipes: List<DetailedRecipe>): Result<String, String> = menuRepository.addMenu(recipes)
    override suspend fun deleteMenu(id: Int, recipeIds: List<Int>): Result<String, String> {
        return try {
            menuRepository.deleteMenu(id)
            // 外部キー制約
            try {
                recipeRepository.deleteRecipes(recipeIds)
            } catch (e: Exception) {
            }
            Ok("Success")
        } catch (e: Exception) {
            Err(e.toString())
        }
    }
    override suspend fun updateMenu(menu: Menu): Result<String, String> = menuRepository.updateMenu(menu)
    override suspend fun checkShoppingItem(id: Int): Result<String, String> = menuRepository.checkShoppingItem(id)
}

