package com.example.recipe_app.use_cases

import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.repositories.TestRepository
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import javax.inject.Inject

interface TestUseCase {
    suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String>
    suspend fun fetchFavorites(): Result<Favorites, String>
    suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String>
    suspend fun addFavoriteMenu(menu: Menu): Result<String, String>
    suspend fun fetchMenus(): Result<List<Menu>, String>
    suspend fun addMenu(menu: Menu): Result<String, String>
}

class TestUseCaseImpl @Inject constructor(
    private val testRepository: TestRepository
): TestUseCase {
    override suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String> {
        return testRepository.fetchRecipes(conditions)
    }

    override suspend fun fetchFavorites(): Result<Favorites, String> {
        return testRepository.fetchFavorites()
    }

    override suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String> {
        return testRepository.addFavoriteRecipe(recipe)
    }

    override suspend fun addFavoriteMenu(menu: Menu): Result<String, String> {
        return testRepository.addFavoriteMenu(menu)
    }

    override suspend fun fetchMenus(): Result<List<Menu>, String> {
        return testRepository.fetchMenus()
    }

    override suspend fun addMenu(menu: Menu): Result<String, String> {
        return testRepository.addMenu(menu)
    }
}