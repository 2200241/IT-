package com.example.recipe_app

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        MyBottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = colorResource(id = R.color.bottomNavigationColor),
                unselectedContentColor = Color.Gray,
                icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = stringResource(id = item.titleId)
                ) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == item.screen.route
                } == true,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

private sealed class MyBottomNavigationItem(
    val screen: Screen,
    val titleId: Int,
    val icon: ImageVector
) {
    object MakeMenu: MyBottomNavigationItem(Screen.MakeMenu, R.string.makeMenu_title, Icons.Filled.Fastfood)
    object MenuList: MyBottomNavigationItem(Screen.MenuList, R.string.menuList_title, Icons.Filled.List)
    object Favorites: MyBottomNavigationItem(Screen.FavoriteList, R.string.favoriteList_title, Icons.Filled.Favorite)
    object Settings: MyBottomNavigationItem(Screen.Settings, R.string.settings_title, Icons.Filled.Settings)
}

private val MyBottomNavigationItems = listOf(
    MyBottomNavigationItem.MakeMenu,
    MyBottomNavigationItem.MenuList,
    MyBottomNavigationItem.Favorites,
    MyBottomNavigationItem.Settings
)