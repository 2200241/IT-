package com.example.recipe_app.favorite_list

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
// 上書き防止
internal sealed class Screen(val route: String) {
    object SelectFavorite : Screen("selectFavorite")
    object ShoppingList : Screen("shoppingList/{menuId}/") {
        fun createRoute(menuId: Int) = "shoppingList/$menuId"
    }
    object RecipeDetail : Screen("recipeDetail/{recipeId}/{thumb}") {
        fun createRoute(recipeId: Int, thumb: String) = "recipeDetail/$recipeId/$thumb"
    }
}

class FavoriteListScreenState(
    private val viewModel: FavoriteListViewModel,
    private val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    val navController: NavHostController
) {
    val uiState: FavoriteListUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToShoppingList(id: Int, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.ShoppingList.createRoute(id))
        }
    }

    fun navigateToRecipeDetail(recipeId: Int, thumb: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            if (recipeId >= 0) {
                navController.navigate(Screen.RecipeDetail.createRoute(recipeId, thumb))
            } else {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "No recipe is selected.",
                        actionLabel = "OK"
                    )
                }
            }
        }
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}

@Composable
fun rememberFavoriteListScreenState(
    viewModel: FavoriteListViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): FavoriteListScreenState = remember {
    FavoriteListScreenState(viewModel, scaffoldState, coroutineScope, navController)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
