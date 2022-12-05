package com.example.recipe_app.use_cases

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.TestRepository
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface TestUseCase {
    suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String>
    suspend fun fetchRecipeDetail(id: String): Result<RecipeDetail, String>
    fun fetchFavorites(): Flow<Result<Favorites, String>>
    suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String>
    suspend fun removeFavoriteRecipe(id: String): Result<String, String>
    suspend fun addFavoriteMenu(menu: Menu): Result<String, String>
    suspend fun removeFavoriteMenu(id: String): Result<String, String>
    suspend fun fetchMenus(): Flow<Result<List<Menu>, String>>
    suspend fun fetchMenu(id: String): Result<List<RecipeThumb>, String>
    suspend fun fetchShoppingList(id: String): Result<List<Ingredient>, String>
    suspend fun addMenu(menu: Menu): Result<String, String>
    suspend fun removeMenu(id: String): Result<String, String>
    suspend fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>>
    suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String>
    suspend fun removeFromTempMenu(id: String): Result<String, String>
    suspend fun removeAllFromTempMenu(): Result<String, String>
}

class TestUseCaseImpl @Inject constructor(
    private val testRepository: TestRepository
): TestUseCase {

    override suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String> {
        return testRepository.fetchRecipes(conditions)
    }

    override suspend fun fetchRecipeDetail(id: String): Result<RecipeDetail, String> {
        return testRepository.fetchRecipeDetail(id)
    }

    override fun fetchFavorites(): Flow<Result<Favorites, String>> {
        return testRepository.fetchFavorites()
    }

    override suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String> {
        return testRepository.addFavoriteRecipe(recipe)
    }

    override suspend fun removeFavoriteRecipe(id: String): Result<String, String> {
        return testRepository.removeFavoriteRecipe(id)
    }

    override suspend fun addFavoriteMenu(menu: Menu): Result<String, String> {
        return testRepository.addFavoriteMenu(menu)
    }

    override suspend fun removeFavoriteMenu(id: String): Result<String, String> {
        return testRepository.removeFavoriteMenu(id)
    }

    override suspend fun fetchMenus(): Flow<Result<List<Menu>, String>> {
        return testRepository.fetchMenus()
    }

    override suspend fun fetchMenu(id: String): Result<List<RecipeThumb>, String> {
        return testRepository.fetchMenu(id)
    }

    override suspend fun fetchShoppingList(id: String): Result<List<Ingredient>, String> {
        return testRepository.fetchShoppingList(id)
    }

    override suspend fun addMenu(menu: Menu): Result<String, String> {
        return testRepository.addMenu(menu)
    }

    override suspend fun removeMenu(id: String): Result<String, String> {
        return testRepository.removeMenu(id)
    }

    override suspend fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>> {
        return testRepository.getTempMenu()
    }

    override suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String> {
        return testRepository.addToTempMenu(recipe)
    }

    override suspend fun removeFromTempMenu(id: String): Result<String, String> {
        return testRepository.removeFromTempMenu(id)
    }

    override suspend fun removeAllFromTempMenu(): Result<String, String> {
        return testRepository.removeAllFromTempMenu()
    }
}