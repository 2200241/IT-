package com.example.recipe_app.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetail(
    state: RecipeDetailState,
    onBackPressed: () -> Unit = {}
) {
    Text(text = state.uiState.title)
}