package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipe_app.data.MenuWithRecipeThumbs
import com.github.michaelbull.result.mapBoth
// 上書き防止
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

    fun checkShoppingListItem(index: Int) = viewModel.checkShoppingListItem(index)
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