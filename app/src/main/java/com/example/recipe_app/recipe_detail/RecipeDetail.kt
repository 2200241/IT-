package com.example.recipe_app.recipe_detail

import androidx.compose.runtime.Composable

@Composable
fun RecipeDetail(
    recipeId: String?,
    state: RecipeDetailState = rememberRecipeDetailState(recipeId = recipeId)
) {

}