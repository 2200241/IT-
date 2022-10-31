package com.example.recipe_app

import android.annotation.SuppressLint
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
import com.example.recipe_app.make_menu.MakeMenuScreen
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Surface() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { MyBottomNavigation(navController = navController) }
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.MakeMenu.route
            ) {
                composable(Screen.MakeMenu.route) { MakeMenuScreen() }
                composable(Screen.MenuList.route) {}
                composable(Screen.Favorites.route) {}
                composable(Screen.Settings.route) {}
            }
        }
    }
}

internal sealed class Screen(val route: String) {
    object MakeMenu : Screen("makeMenu")
    object MenuList : Screen("menuList")
    object Favorites : Screen("favorites")
    object Settings : Screen("settings")
}