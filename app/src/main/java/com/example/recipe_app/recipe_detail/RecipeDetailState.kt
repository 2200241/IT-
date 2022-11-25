package com.example.recipe_app.recipe_detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

class RecipeDetailState(
    private val viewModel: RecipeDetailViewModel,
) {
    val uiState: RecipeDetailUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun test() {
        Log.d("debug", viewModel.toString())
    }

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