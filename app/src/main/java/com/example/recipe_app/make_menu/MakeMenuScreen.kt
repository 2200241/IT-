package com.example.recipe_app.make_menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipe_app.make_menu.select_conditions.SelectConditions
import com.example.recipe_app.make_menu.select_recipes.SelectRecipes
import com.example.recipe_app.recipe_detail.RecipeDetail

@Composable
fun MakeMenuScreen(
    state: MakeMenuScreenState = rememberMakeMenuScreenState()
) {
    val uiState = state.uiState

    NavHost(
        navController = state.navController,
        startDestination = Screen.SelectConditions.route
    ) {
        composable(Screen.SelectConditions.route) { SelectConditions() }
        composable(Screen.SelectRecipes.route) { backStackEntry ->
            SelectRecipes(
                onItemClicked = { recipeId ->
                    state.navigateToRecipeDetail(recipeId, backStackEntry)
                }
            )
        }
        composable(
            route = "recipeDetail/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            RecipeDetail(recipeId = backStackEntry.arguments?.getString("recipeId"))
        }
    }
}