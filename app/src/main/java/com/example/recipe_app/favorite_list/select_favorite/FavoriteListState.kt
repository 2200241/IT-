package com.example.recipe_app.favorite_list.select_favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class SelectFavoriteState(
    private val viewModel: SelectFavoriteViewModel
) {
    val uiState: SelectFavoriteUiState
        @Composable get() = viewModel.uiState.collectAsState().value

}

@Composable
fun rememberSelectFavoriteState(
    viewModel: SelectFavoriteViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): SelectFavoriteState = remember {
    SelectFavoriteState(viewModel)
}