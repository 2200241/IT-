package com.example.recipe_app.recipe_detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.Recipe
import com.github.michaelbull.result.mapBoth

class RecipeDetailState(
    private val viewModel: RecipeDetailViewModel,
) {
    val uiState: RecipeDetailUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favoriteRecipeIds
        @Composable get() = viewModel.favoriteRecipeIds.collectAsState().value.mapBoth(
            success = { favorites ->  favorites.recipes.map { it.id } },
            failure = { emptyList<String>() }
        )

    fun addFavoriteRecipe() = viewModel.addFavoriteRecipe()

    fun removeFavoriteRecipe() = viewModel.removeFavoriteRecipe()

    fun addToTempMenu() = viewModel.addToTempMenu()
}

//viewModel()を使うと同じインスタンスが返されてしまう？
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