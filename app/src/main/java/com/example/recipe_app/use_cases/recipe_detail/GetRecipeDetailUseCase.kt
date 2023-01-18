package com.example.recipe_app.use_cases.recipe_detail

import com.example.recipe_app.repositories.ApiRepository

class GetRecipeDetailUseCase(
    private val apiRepository: ApiRepository
) {
    suspend fun fetch(recipeId: String) {
        apiRepository.searchRecipeId(recipeId)
    }
}