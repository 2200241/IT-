package com.example.recipe_app.favorite_list.select_favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.Favorites
import com.github.michaelbull.result.mapBoth

class SelectFavoriteState(
    private val viewModel: SelectFavoriteViewModel
) {
    val uiState: SelectFavoriteUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favorites: Favorites
        @Composable get() = viewModel.favorites.collectAsState().value.mapBoth(
            success = { favorites -> favorites },
            failure = { Favorites() }
        )

    fun onTabClicked(selectedTab: FavoriteTab) = viewModel.onTabClicked(selectedTab)

    fun onTabClicked(selectedTab: FavoriteCategoryTab) = viewModel.onTabClicked(selectedTab)

}

@Composable
fun rememberSelectFavoriteState(
    viewModel: SelectFavoriteViewModel = hiltViewModel()
): SelectFavoriteState = remember {
    SelectFavoriteState(viewModel)
}