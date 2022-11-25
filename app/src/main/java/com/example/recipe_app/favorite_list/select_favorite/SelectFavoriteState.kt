package com.example.recipe_app.favorite_list.select_favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

class SelectFavoriteState(
    private val viewModel: SelectFavoriteViewModel
) {
    val uiState: SelectFavoriteUiState
        @Composable get() = viewModel.uiState.collectAsState().value

}

@Composable
fun rememberSelectFavoriteState(
    viewModel: SelectFavoriteViewModel = hiltViewModel()
): SelectFavoriteState = remember {
    SelectFavoriteState(viewModel)
}