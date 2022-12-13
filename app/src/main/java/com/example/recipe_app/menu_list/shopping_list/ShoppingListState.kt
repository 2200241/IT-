package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.MenuWithRecipeThumbs
import com.github.michaelbull.result.mapBoth

class ShoppingListState(
    private val viewModel: ShoppingListViewModel,
) {
    val uiState: ShoppingListUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val menuWithRecipeThumbs: MenuWithRecipeThumbs
        @Composable get() = viewModel.menuWithRecipeThumbs.collectAsState().value.mapBoth(
            success = { it },
            failure = { MenuWithRecipeThumbs() }
        )

    val favoriteMenuIds: List<Int>
        @Composable get() = viewModel.favorites.collectAsState().value.mapBoth(
            success = { favorites -> favorites.menuWithoutIngredients.map { it.id } },
            failure = { emptyList() }
        )

    fun checkShoppingListItem(index: Int) = viewModel.checkShoppingListItem(index)

    fun addFavorite(menu: MenuWithRecipeThumbs) = viewModel.addFavorite(menu)

    fun removeFavorite(id: Int) = viewModel.removeFavorite(id)
}

@Composable
fun rememberShoppingListState(
    viewModel: ShoppingListViewModel = hiltViewModel()
/*
    menuId: String?,
    viewModel: ShoppingListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ShoppingListViewModel.Factory(menuId)
    ),
*/
): ShoppingListState = remember {
    ShoppingListState(viewModel)
}