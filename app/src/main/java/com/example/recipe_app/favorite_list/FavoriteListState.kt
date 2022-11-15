package com.example.recipe_app.favorite_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class FavoriteListState(
    private val viewModel: FavoriteListViewModel
) {
    val uiState: FavoriteListUiState
        @Composable get() = viewModel.uiState.collectAsState().value

}

@Composable
fun rememberFavoriteListState(
    viewModel: FavoriteListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
): FavoriteListState = remember {
    FavoriteListState(viewModel)
}