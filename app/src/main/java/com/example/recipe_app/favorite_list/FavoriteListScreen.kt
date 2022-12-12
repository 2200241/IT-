package com.example.recipe_app.favorite_list

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipe_app.favorite_list.select_favorite.SelectFavorite
import com.example.recipe_app.menu_list.shopping_list.ShoppingList
import com.example.recipe_app.recipe_detail.RecipeDetail
// 上書き防止
@Composable
fun FavoriteListScreen(
    state: FavoriteListScreenState,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = state.navController,
        startDestination = Screen.SelectFavorite.route,
    ) {
        composable(Screen.SelectFavorite.route) { backStackEntry ->
            SelectFavorite(
                paddingValues = paddingValues,
                onRecipeClicked = { id, thumb ->
                    state.navigateToRecipeDetail(id, thumb, backStackEntry)
                },
                onMenuClicked = { id ->
                    state.navigateToShoppingList(id, backStackEntry)
                }
            )
        }
        composable(
            route = "recipeDetail/{recipeId}/{thumb}",
//            arguments = listOf(navArgument("conditions") { type = NavType.StringType })
        ) { backStackEntry ->
            RecipeDetail(
                paddingValues = paddingValues,
                addButtonIsDisplayed = false,
                onBackPressed = state::navigateBack
            )
        }
        composable(
            route = "shoppingList/{menuId}",
//            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { backStackEntry ->
            ShoppingList(
                paddingValues = paddingValues,
                onThumbClicked = { id, thumb ->
                    state.navigateToRecipeDetail(id, thumb, backStackEntry)
                }
            )
        }
    }
}