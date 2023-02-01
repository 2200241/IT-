package com.example.recipe_app.menu_list.select_menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.RecipeWithoutCategory
import com.github.michaelbull.result.mapBoth

class SelectMenuState(
    private val viewModel: SelectMenuViewModel
) {
    val uiState: SelectMenuUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val menus: Map<Menu, List<RecipeWithoutCategory>>
        @Composable get() = viewModel.menus.collectAsState().value

    val favoriteMenuIds: List<Int>
        @Composable get() = viewModel.favoriteMenuIds.collectAsState().value

    fun removeMenu(menuId: Int) = viewModel.removeMenu(menuId)

    fun addFavoriteMenu(menuId: Int) = viewModel.addFavoriteMenu(menuId)

    fun removeFavoriteMenu(menuId: Int) = viewModel.removeFavoriteMenu(menuId)
}

@Composable
fun rememberSelectMenuState(
    viewModel: SelectMenuViewModel = hiltViewModel()
): SelectMenuState = remember {
    SelectMenuState(viewModel)
}