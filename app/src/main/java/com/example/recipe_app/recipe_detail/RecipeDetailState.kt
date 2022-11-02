package com.example.recipe_app.recipe_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class RecipeDetailState(
    private val recipeId: String?,
    private val viewModel: RecipeDetailViewModel,
) {
    val uiState: RecipeDetailUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun getRecipeData() = viewModel.getRecipeData()
}

//viewModel()を使うと同じインスタンスが返されてしまう？
@Composable
fun rememberRecipeDetailState(
    recipeId: String?,
    viewModel: RecipeDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = RecipeDetailViewModel.provideFactory(recipeId = recipeId)
    )
): RecipeDetailState = remember {
    RecipeDetailState(recipeId, viewModel)
}