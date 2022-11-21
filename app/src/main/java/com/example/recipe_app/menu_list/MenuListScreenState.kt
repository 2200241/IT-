package com.example.recipe_app.menu_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

internal sealed class Screen(val route: String) {
    object SelectMenu : Screen("selectMenu")
    object ShoppingList : Screen("shoppingList/{menuId}") {
        fun createRoute(menuId: String) = "shoppingList/$menuId"
    }
}

class MenuListScreenState(
    private val viewModel: MenuListViewModel,
    val navController: NavHostController
) {
    val uiState: MenuListUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToShoppingList(id: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.ShoppingList.createRoute(id))
        }
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMenuListScreenState(
    viewModel: MenuListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
): MenuListScreenState = remember {
    MenuListScreenState(viewModel, navController)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
