package com.example.recipe_app.favorite_list.select_favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.MenuWithoutIngredients
import com.example.recipe_app.data.RecipeWithCategoryId
import com.github.michaelbull.result.mapBoth

class SelectFavoriteState(
    private val viewModel: SelectFavoriteViewModel
) {
    val uiState: SelectFavoriteUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val favorites: Favorites
        @Composable get() = viewModel.favorites.collectAsState().value.mapBoth(
            success = { it },
            failure = { Favorites() }
        )

    fun addFavoriteRecipe(recipe: RecipeWithCategoryId) = viewModel.addFavoriteRecipe(recipe)

    fun addFavoriteMenu(menu: MenuWithoutIngredients) = viewModel.addFavoriteMenu(menu)

    fun removeFavoriteRecipe(id: Int) = viewModel.removeFavoriteRecipe(id)

    fun removeFavoriteMenu(id: Int) = viewModel.removeFavoriteMenu(id)

    fun onTabClicked(selectedTab: FavoriteTab) = viewModel.onTabClicked(selectedTab)

    fun onTabClicked(selectedTab: FavoriteCategoryTab) = viewModel.onTabClicked(selectedTab)

}

@Composable
fun rememberSelectFavoriteState(
    viewModel: SelectFavoriteViewModel = hiltViewModel()
): SelectFavoriteState = remember {
    SelectFavoriteState(viewModel)
}