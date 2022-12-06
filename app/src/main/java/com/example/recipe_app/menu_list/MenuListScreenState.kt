package com.example.recipe_app.menu_list

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal sealed class Screen(val route: String) {
    object SelectMenu : Screen("selectMenu")
    object ShoppingList : Screen("shoppingList/{menuId}/") {
        fun createRoute(menuId: String) = "shoppingList/$menuId"
    }
    object RecipeDetail : Screen("recipeDetail/{recipeId}/{thumb}") {
        fun createRoute(recipeId: String, thumb: String) = "recipeDetail/$recipeId/$thumb"
    }
}

class MenuListScreenState(
    private val viewModel: MenuListViewModel,
    private val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    val navController: NavHostController
) {
    val uiState: MenuListUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToShoppingList(id: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.ShoppingList.createRoute(id))
        }
    }

    fun navigateToRecipeDetail(recipeId: String, thumb: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            if (recipeId.isBlank()) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "No recipe is selected.",
                        actionLabel = "OK"
                    )
                }
            } else {
                navController.navigate(Screen.RecipeDetail.createRoute(recipeId, thumb))
            }
        }
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMenuListScreenState(
    viewModel: MenuListViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): MenuListScreenState = remember {
    MenuListScreenState(viewModel, scaffoldState, coroutineScope, navController)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
