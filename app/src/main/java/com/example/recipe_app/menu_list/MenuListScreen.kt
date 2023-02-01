package com.example.recipe_app.menu_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipe_app.menu_list.select_menu.SelectMenu
import com.example.recipe_app.menu_list.shopping_list.ShoppingList
import com.example.recipe_app.recipe_detail.RecipeDetail

@Composable
fun MenuListScreen(
    state: MenuListScreenState,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = state.navController,
        startDestination = Screen.SelectMenu.route,
    ) {
        composable(Screen.SelectMenu.route) { backStackEntry ->
            SelectMenu(
                paddingValues = paddingValues,
                onItemClicked = { id ->
                    state.navigateToShoppingList(id, backStackEntry)
                },
            )
        }
        composable(
            route = "shoppingList/{menuId}",
//            arguments = listOf(navArgument("menuId") { type = NavType.StringType })
        ) { backStackEntry ->
            ShoppingList(
//                state = rememberShoppingListState(menuId = backStackEntry.arguments?.getString("menuId")),
                paddingValues = paddingValues,
                onThumbClicked = { id, thumb ->
                    state.navigateToRecipeDetail(id, thumb, backStackEntry)
                }
            )
        }
        composable(
            route = "recipeDetail/{recipeId}",
//            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            RecipeDetail(
//                state = rememberRecipeDetailState(recipeId = backStackEntry.arguments?.getString("recipeId")),
                paddingValues = paddingValues,
                onBackPressed = { state.navigateBack() }
            )
        }
    }
}