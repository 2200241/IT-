package com.example.recipe_app.make_menu

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
    object RecipeDetail : Screen("recipeDetail/{recipeId}") {
        fun createRoute(recipeId: String) = "recipeDetail/$recipeId"
    }
}

class MakeMenuScreenState(
    private val viewModel: MakeMenuViewModel,
    private val scaffoldState: ScaffoldState,
    val coroutineScope: CoroutineScope,
    val navController: NavHostController
) {
    val uiState: MakeMenuUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToSelectRecipes(conditions: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            if (conditions.isNullOrBlank()) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "条件を選択してください",
                        actionLabel = "OK"
                    )
                }
            } else {
                navController.navigate(Screen.SelectRecipes.createRoute(conditions))
            }
        }
    }

    fun navigateToRecipeDetail(recipeId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            if (recipeId.isNullOrBlank()) {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "No recipe is selected.",
                        actionLabel = "OK"
                    )
                }
            } else {
                navController.navigate(Screen.RecipeDetail.createRoute(recipeId))
            }
        }
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMakeMenuScreenState(
    viewModel: MakeMenuViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
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
