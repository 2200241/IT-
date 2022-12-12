package com.example.recipe_app.menu_list.select_menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.MenuWithoutIngredients
import com.github.michaelbull.result.mapBoth

class SelectMenuState(
    private val viewModel: SelectMenuViewModel
) {
    val uiState: SelectMenuUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val menus: List<MenuWithoutIngredients>
        @Composable get() = viewModel.menus.collectAsState().value.mapBoth(
            success = { it },
            failure = { emptyList() }
        )

    val favoriteMenus: List<MenuWithoutIngredients>
        @Composable get() = viewModel.favorites.collectAsState().value.mapBoth(
            success = { favorites -> favorites.menuWithoutIngredients },
            failure = { emptyList() }
        )

    fun removeMenu(id: Int) = viewModel.removeMenu(id)

    fun addFavoriteMenu(menu: MenuWithoutIngredients) = viewModel.addFavoriteMenu(menu)

    fun removeFavoriteMenu(id: Int) = viewModel.removeFavoriteMenu(id)
}

@Composable
fun rememberSelectMenuState(
    viewModel: SelectMenuViewModel = hiltViewModel()
): SelectMenuState = remember {
    SelectMenuState(viewModel)
}