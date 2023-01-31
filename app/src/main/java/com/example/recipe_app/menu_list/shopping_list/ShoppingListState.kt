package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.MenuWithRecipeThumbs
import com.example.recipe_app.data.RecipeWithoutCategory
import com.example.recipe_app.data.ShoppingItemWithIngredient
import com.github.michaelbull.result.mapBoth

class ShoppingListState(
    private val viewModel: ShoppingListViewModel,
) {
    val uiState: ShoppingListUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val menuDetail: Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>
        @Composable get() = viewModel.menuDetail.collectAsState().value

    val favoriteMenuIds: List<Int>
        @Composable get() = viewModel.favoriteMenuIds.collectAsState().value

    fun checkShoppingListItem(index: Int) = viewModel.checkShoppingListItem(index)

    fun addFavorite(id: Int) = viewModel.addFavorite(id)

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