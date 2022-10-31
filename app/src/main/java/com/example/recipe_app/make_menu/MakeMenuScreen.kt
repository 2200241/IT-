package com.example.recipe_app.make_menu

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipe_app.make_menu.select_conditions.SelectConditions
import com.example.recipe_app.make_menu.select_recipes.SelectRecipes

@Composable
fun MakeMenuScreen(
    state: MakeMenuScreenState = rememberMakeMenuScreenState()
) {
    val uiState = state.uiState

    NavHost(
        navController = state.navController,
        startDestination = Screen.SelectConditions.route
    ) {
        composable(Screen.SelectConditions.route) { SelectConditions()}
        composable(Screen.SelectRecipes.route) { SelectRecipes() }
        composable(Screen.RecipeDetail.route) {}
    }
}