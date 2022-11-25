package com.example.recipe_app.use_cases

import com.example.recipe_app.data.Recipe
import com.example.recipe_app.repositories.TestRepository
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import javax.inject.Inject

interface TestUseCase {
    suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String>
}

class TestUseCaseImpl @Inject constructor(
    private val testRepository: TestRepository
): TestUseCase {
    override suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String> {
        return testRepository.fetchRecipes(conditions)
    }
}