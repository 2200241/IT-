package com.example.recipe_app.make_menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

internal sealed class Screen(val route: String) {
    object SelectConditions : Screen("selectConditions")
    object SelectRecipes : Screen("selectRecipes")
    object RecipeDetail : Screen("recipeDetail")
}

class MakeMenuScreenState(
    private val viewModel: MakeMenuViewModel,
    val navController: NavHostController
) {
    val uiState: MakeMenuViewModel.UiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun navigateToSelectRecipes() {
        navController.navigate(Screen.SelectRecipes.route)
    }

    fun navigateToRecipeDetail() {
//        navController.navigate(Screen.RecipeDetail.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberMakeMenuScreenState(
    viewModel: MakeMenuViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController = rememberNavController()
): MakeMenuScreenState = remember {
    MakeMenuScreenState(viewModel, navController)
}