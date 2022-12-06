package com.example.recipe_app.favorite_list.select_favorite

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.make_menu.select_recipes.SearchResultRecipes
import com.example.recipe_app.menu_list.select_menu.MenuListItem

@Composable
fun SelectFavorite(
    state: SelectFavoriteState = rememberSelectFavoriteState(),
    paddingValues: PaddingValues
) {
    val uiState = state.uiState
    val favorites = state.favorites

    Column(modifier = Modifier.padding(paddingValues)) {
        SelectFavoritesTabBar(
            selectedTab = uiState.selectedMainTab,
            onClick = state::onTabClicked
        )

        LazyColumn() {
            when (uiState.selectedMainTab) {
                FavoriteTab.SelectRecipeTab -> {
                    item { FavoriteRecipeList(state) }
/*
                    favorites.recipes.forEach { recipe ->
                        item {
                            FavoriteRecipeListItem(recipe, favorites.recipes)
                            Divider(color = Color.LightGray)
                        }
                    }
*/
                }
                FavoriteTab.SelectMenuTab -> {
                    favorites.menus.forEach { menu ->
                        item {
                            FavoriteMenuListItem(menu, favorites.menus)
                            Divider(color = Color.LightGray)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SelectFavoritesTabBar(
    modifier: Modifier = Modifier,
    selectedTab: FavoriteTab,
    onClick: (FavoriteTab) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.index,
        backgroundColor = Color.White,
    ) {
        FavoriteTabs.forEach { item ->
            Tab(
                modifier = Modifier.height(50.dp),
                selected = item.index == selectedTab.index,
                selectedContentColor = colorResource(id = R.color.fontColor),
                unselectedContentColor = Color.Gray,
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    text = stringResource(id = item.titleId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SelectFavoriteCategoriesTabBar(
    modifier: Modifier = Modifier,
    selectedTab: FavoriteCategoryTab,
    onClick: (FavoriteCategoryTab) -> Unit
) {
    ScrollableTabRow(
        modifier = modifier.padding(horizontal = 5.dp),
        selectedTabIndex = selectedTab.index,
        backgroundColor = Color.White,
        contentColor = Color.Transparent,
        edgePadding = 0.dp
    ) {
        FavoriteCategoryTabs.forEach { item ->
            val selected = selectedTab.index == item.index
            val backgroundColor = if (selected) colorResource(id = R.color.categoryTabColor) else Color.White

            Tab(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .border(BorderStroke(1.5.dp, colorResource(id = R.color.categoryTabColor)), CircleShape),
                selected = item.index == selectedTab.index,
                selectedContentColor = Color.White,
                unselectedContentColor = colorResource(id = R.color.fontColor),
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = stringResource(id = item.titleId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private val FavoriteTabs = listOf(
    FavoriteTab.SelectRecipeTab,
    FavoriteTab.SelectMenuTab
)

private val FavoriteCategoryTabs = listOf(
    FavoriteCategoryTab.SelectStapleFoodTab,
    FavoriteCategoryTab.SelectMainDishTab,
    FavoriteCategoryTab.SelectSideDishTab,
    FavoriteCategoryTab.SelectSoupTab,
    FavoriteCategoryTab.SelectSweetsTab,
    FavoriteCategoryTab.SelectDrinkTab,
    FavoriteCategoryTab.SelectOthersTab
)

@Composable
fun FavoriteRecipeList(state: SelectFavoriteState) {
    val uiState = state.uiState

    Column() {
        SelectFavoriteCategoriesTabBar(
            selectedTab = uiState.selectedSubTab,
            onClick = state::onTabClicked
        )
        when (uiState.selectedSubTab) {
            FavoriteCategoryTab.SelectStapleFoodTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
            FavoriteCategoryTab.SelectMainDishTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
            FavoriteCategoryTab.SelectSideDishTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
            FavoriteCategoryTab.SelectSoupTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
            FavoriteCategoryTab.SelectSweetsTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
            FavoriteCategoryTab.SelectDrinkTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
            FavoriteCategoryTab.SelectOthersTab -> {
                for (i in 1..10) {
                    FavoriteRecipeListItem("料理名$i")
                    Divider(color = Color.LightGray)
                }
            }
        }
    }
}
@Composable
fun FavoriteRecipeListItem(
    recipe: Recipe,
    recipes: List<Recipe>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { Log.d("favoriteListItem", "onClick") },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Box( // ->image
                modifier = Modifier
                    .size(115.dp, 75.dp)
                    .background(color = Color.LightGray)
            ) {
                Text(text = recipe.thumb, color = Color.White)
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = recipe.title,
                fontSize = 18.sp,
                color = colorResource(id = R.color.fontColor)
            )
        }
        Icon(
            Icons.Sharp.Favorite,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(alignment = Alignment.Bottom)
                .clickable { },
            tint = if (recipes.contains(recipe))
                colorResource(id = R.color.favoriteIconColor)
                else Color.Gray
        )
    }
}

@Composable
fun FavoriteMenuListItem(
    menu: Menu,
    menus: List<Menu>
) {
    MenuListItem(onItemClicked = {})
}