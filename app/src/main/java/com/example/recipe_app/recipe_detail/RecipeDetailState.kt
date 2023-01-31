package com.example.recipe_app.recipe_detail

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.michaelbull.result.mapBoth

class RecipeDetailState(
    private val viewModel: RecipeDetailViewModel,
    val scaffoldState: ScaffoldState?
) {
    val uiState: RecipeDetailUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favoriteRecipeIds
        @Composable get() = viewModel.favoriteRecipeIds.collectAsState().value

    fun addFavoriteRecipe() = viewModel.addFavoriteRecipe()

    fun removeFavoriteRecipe() = viewModel.removeFavoriteRecipe()

//    fun addToTempMenu() = viewModel.addToTempMenu()

    fun resetMessage() = viewModel.resetMessage()
}

@Composable
fun rememberRecipeDetailState(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState? = null
/*
    recipeId: String?,
    viewModel: RecipeDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = RecipeDetailViewModel.Factory(recipeId)
    )
*/
): RecipeDetailState = remember {
    RecipeDetailState(viewModel, scaffoldState)
}