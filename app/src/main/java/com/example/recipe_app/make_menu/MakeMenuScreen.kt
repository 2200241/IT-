package com.example.recipe_app.make_menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipe_app.make_menu.select_conditions.SelectConditions
import com.example.recipe_app.make_menu.select_recipes.SelectRecipes
import com.example.recipe_app.make_menu.select_recipes.rememberSelectRecipesState
import com.example.recipe_app.recipe_detail.RecipeDetail
import com.example.recipe_app.recipe_detail.rememberRecipeDetailState

@Composable
fun MakeMenuScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    state: MakeMenuScreenState
) {
    val uiState = state.uiState

    NavHost(
        navController = state.navController,
        startDestination = Screen.SelectConditions.route,
    ) {
        composable(Screen.SelectConditions.route) { backStackEntry ->
            SelectConditions(
                padding = paddingValues,
                onSearchClicked = { conditions ->
                    state.navigateToSelectRecipes(conditions, backStackEntry)
                },
            )
        }
        composable(
            route = "selectRecipes/{conditions}",
            arguments = listOf(navArgument("conditions") { type = NavType.StringType })
        ) { backStackEntry ->
            SelectRecipes(
                state = rememberSelectRecipesState(conditions = backStackEntry.arguments?.getString("conditions")),
                paddingValues = paddingValues,
                onItemClicked = { recipeId ->
                    state.navigateToRecipeDetail(recipeId, backStackEntry)
                },
                onBackPressed = { state.navigateBack() }
            )
        }
        composable(
            route = "recipeDetail/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            RecipeDetail(
                state = rememberRecipeDetailState(recipeId = backStackEntry.arguments?.getString("recipeId")),
                onBackPressed = { state.navigateBack() }
            )
        }
    }
}