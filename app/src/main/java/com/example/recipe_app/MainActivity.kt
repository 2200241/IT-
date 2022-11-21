package com.example.recipe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe_app.favorite_list.FavoriteListScreen
import com.example.recipe_app.make_menu.MakeMenuScreen
import com.example.recipe_app.make_menu.rememberMakeMenuScreenState
import com.example.recipe_app.menu_list.MenuListScreen
import com.example.recipe_app.menu_list.rememberMenuListScreenState
import com.example.recipe_app.settings.SettingsScreen
import com.example.recipe_app.ui.theme.RecipeappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = scaffoldState.snackbarHostState) },
            bottomBar = { MyBottomNavigation(navController = navController) }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.MakeMenu.route,
            ) {
                composable(Screen.MakeMenu.route) {
                    MakeMenuScreen(
                        paddingValues = paddingValues,
                        state = rememberMakeMenuScreenState(scaffoldState = scaffoldState)
                    )
                }
                composable(Screen.MenuList.route) {
                    MenuListScreen(
                        paddingValues = paddingValues,
                        state = rememberMenuListScreenState(scaffoldState = scaffoldState)
                    )
                }
                composable(Screen.FavoriteList.route) {
                    FavoriteListScreen(
                        paddingValues = paddingValues
                    )
                }
                composable(Screen.Settings.route) { SettingsScreen() }
            }
        }
    }
}

internal sealed class Screen(val route: String) {
    object MakeMenu : Screen("makeMenu")
    object MenuList : Screen("menuList")
    object FavoriteList : Screen("favoriteList")
    object Settings : Screen("settings")
}