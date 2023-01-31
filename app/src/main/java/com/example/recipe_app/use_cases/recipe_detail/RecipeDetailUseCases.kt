package com.example.recipe_app.use_cases.recipe_detail

import javax.inject.Inject

data class RecipeDetailUseCases @Inject constructor(
    val recipeDetail: RecipeDetailUseCases
)

