package com.example.recipe_app.make_menu

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
    object SelectConditions : Screen("selectConditions")
    object SelectRecipes : Screen("selectRecipes/{conditions}") {
        fun createRoute(conditions: String) = "selectRecipes/$conditions"
    }
    object RecipeDetail : Screen("recipeDetail/{recipeId}/{thumb}") {
        fun createRoute(recipeId: Int, thumb: String) = "recipeDetail/$recipeId/$thumb"
    }
}

class MakeMenuScreenState(
    private val viewModel: MakeMenuViewModel,
    val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope,
    val navController: NavHostController,
) {
    val uiState: MakeMenuUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToSelectRecipes(conditions: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            if (conditions.isBlank()) {
                showSnackBar("条件を選択してください")
            } else {
                navController.navigate(Screen.SelectRecipes.createRoute(conditions))
            }
        }
    }

    fun navigateToRecipeDetail(recipeId: Int, thumb: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            if (recipeId >= 0) {
                navController.navigate(Screen.RecipeDetail.createRoute(recipeId, thumb))
            } else {
                showSnackBar("No recipe is selected.")
            }
        }
    }

    fun showSnackBar(message: String) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "OK"
            )
        }
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMakeMenuScreenState(
    viewModel: MakeMenuViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): MakeMenuScreenState = remember {
    MakeMenuScreenState(viewModel, scaffoldState, coroutineScope, navController)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
