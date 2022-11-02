package com.example.recipe_app.make_menu

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

internal sealed class Screen(val route: String) {
    object SelectConditions : Screen("selectConditions")
    object SelectRecipes : Screen("selectRecipes")
    object RecipeDetail : Screen("recipeDetail/{resipeId}") {
        fun createRoute(recipeId: String) = "recipeDetail/$recipeId"
    }
}

class MakeMenuScreenState(
    private val viewModel: MakeMenuViewModel,
    val navController: NavHostController
) {
    val uiState: MakeMenuUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToSelectRecipes() {
        navController.navigate(Screen.SelectRecipes.route)
    }

    fun navigateToRecipeDetail(recipeId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.RecipeDetail.createRoute(recipeId))
        }
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMakeMenuScreenState(
    viewModel: MakeMenuViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
): MakeMenuScreenState = remember {
    MakeMenuScreenState(viewModel, navController)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED