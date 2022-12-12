package com.example.recipe_app.recipe_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.michaelbull.result.mapBoth
// 上書き防止
class RecipeDetailState(
    private val viewModel: RecipeDetailViewModel,
) {
    val uiState: RecipeDetailUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favoriteRecipeIds
        @Composable get() = viewModel.favoriteRecipeIds.collectAsState().value.mapBoth(
            success = { favorites ->  favorites.recipeWithCategoryIds.map { it.id } },
            failure = { emptyList() }
        )

    fun addFavoriteRecipe() = viewModel.addFavoriteRecipe()

    fun removeFavoriteRecipe() = viewModel.removeFavoriteRecipe()

    fun addToTempMenu() = viewModel.addToTempMenu()
}

@Composable
fun rememberRecipeDetailState(
    viewModel: RecipeDetailViewModel = hiltViewModel()
/*
    recipeId: String?,
    viewModel: RecipeDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = RecipeDetailViewModel.Factory(recipeId)
    )
*/
): RecipeDetailState = remember {
    RecipeDetailState(viewModel)
}