package com.example.recipe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipe_app.favorite_list.FavoriteListScreen
import com.example.recipe_app.favorite_list.rememberFavoriteListScreenState
import com.example.recipe_app.make_menu.MakeMenuScreen
import com.example.recipe_app.make_menu.rememberMakeMenuScreenState
import com.example.recipe_app.menu_list.MenuListScreen
import com.example.recipe_app.menu_list.rememberMenuListScreenState
import com.example.recipe_app.settings.SettingsScreen
import com.example.recipe_app.ui.theme.RecipeappTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeappTheme {
                // A surface container using the 'background' color from the theme
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.White)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                        MainScreen()
                    }
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
                        state = rememberMakeMenuScreenState(scaffoldState = scaffoldState),
                        paddingValues = paddingValues
                    )
                }
                composable(Screen.MenuList.route) {
                    MenuListScreen(
                        state = rememberMenuListScreenState(scaffoldState = scaffoldState),
                        paddingValues = paddingValues
                    )
                }
                composable(Screen.FavoriteList.route) {
                    FavoriteListScreen(
                        state = rememberFavoriteListScreenState(scaffoldState = scaffoldState),
                        paddingValues = paddingValues
                    )
                }
                composable(Screen.Settings.route) {
                    SettingsScreen(
                        paddingValues = paddingValues
                    )
                }
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

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified
    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}