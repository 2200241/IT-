package com.example.recipe_app.make_menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipe_app.make_menu.select_conditions.SelectConditions
import com.example.recipe_app.make_menu.select_recipes.SelectRecipes
import com.example.recipe_app.make_menu.select_recipes.rememberSelectRecipesState
import com.example.recipe_app.recipe_detail.RecipeDetail
import com.example.recipe_app.recipe_detail.rememberRecipeDetailState

@Composable
fun MakeMenuScreen(
    state: MakeMenuScreenState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
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
//            arguments = listOf(navArgument("conditions") { type = NavType.StringType })
        ) { backStackEntry ->
            SelectRecipes(
                state = rememberSelectRecipesState(scaffoldState = state.scaffoldState),
                paddingValues = paddingValues,
                onItemClicked = { recipeId, thumb ->
                    state.navigateToRecipeDetail(recipeId, thumb, backStackEntry)
                },
                onBackPressed = { state.navigateBack() }
            )
        }
        composable(
            route = "recipeDetail/{recipeId}/{thumb}",
//            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) {
            RecipeDetail(
                state = rememberRecipeDetailState(scaffoldState = state.scaffoldState),
                addButtonIsDisplayed = true,
                paddingValues = paddingValues,
                onBackPressed = { state.navigateBack() }
            )
        }
    }
}