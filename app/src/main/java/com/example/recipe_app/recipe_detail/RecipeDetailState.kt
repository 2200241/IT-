package com.example.recipe_app.recipe_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class RecipeDetailState(
    private val viewModel: RecipeDetailViewModel,
) {
    val uiState: RecipeDetailUiState
        @Composable get() = viewModel.uiState.collectAsState().value

}

//viewModel()を使うと同じインスタンスが返されてしまう？
@Composable
fun rememberRecipeDetailState(
    recipeId: String?,
    viewModel: RecipeDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = RecipeDetailViewModel.Factory(recipeId)
    )
): RecipeDetailState = remember {
    RecipeDetailState(viewModel)
}