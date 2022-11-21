package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class ShoppingListState(
    private val viewModel: ShoppingListViewModel,
) {
    val uiState: ShoppingListUiState
        @Composable get() = viewModel.uiState.collectAsState().value
}

@Composable
fun rememberShoppingListState(
    menuId: String?,
    viewModel: ShoppingListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ShoppingListViewModel.Factory(menuId)
    ),
): ShoppingListState = remember {
    ShoppingListState(viewModel)
}