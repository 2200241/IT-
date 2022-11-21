package com.example.recipe_app.menu_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipe_app.make_menu.select_recipes.SelectRecipes
import com.example.recipe_app.make_menu.select_recipes.rememberSelectRecipesState
import com.example.recipe_app.menu_list.select_menu.SelectMenu
import com.example.recipe_app.menu_list.shopping_list.ShoppingList
import com.example.recipe_app.menu_list.shopping_list.rememberShoppingListState

@Composable
fun MenuListScreen(
    state: MenuListScreenState = rememberMenuListScreenState(),
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
            arguments = listOf(navArgument("menuId") { type = NavType.StringType})
        ) { backStackEntry ->
            ShoppingList(
                state = rememberShoppingListState(menuId = backStackEntry.arguments?.getString("menuId"))
            )
        }
    }
}