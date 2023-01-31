package com.example.recipe_app.favorite_list.select_favorite

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
import coil.compose.AsyncImage
import com.example.recipe_app.R
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
import com.example.recipe_app.data.RecipeWithCategory
import com.example.recipe_app.menu_list.select_menu.MenuListItem

@Composable
fun SelectFavorite(
    state: SelectFavoriteState = rememberSelectFavoriteState(),
    paddingValues: PaddingValues,
    onRecipeClicked: (Int, String) -> Unit,
    onMenuClicked: (Int) -> Unit
) {
    val uiState = state.uiState
    val favoriteRecipes = state.favoriteRecipes
    val favoriteMenus = state.favoriteMenus

    Column(modifier = Modifier.padding(paddingValues)) {
        SelectFavoritesTabBar(
            selectedTab = uiState.selectedMainTab,
            onClick = state::onTabClicked
        )

        when (uiState.selectedMainTab) {
            FavoriteTab.SelectRecipeTab -> {
                Column {
                    SelectFavoriteCategoriesTabBar(
                        selectedTab = uiState.selectedSubTab,
                        onClick = state::onTabClicked
                    )

                    LazyColumn {
                        favoriteRecipes.filter {
                            it.categoryId == uiState.selectedSubTab.index + 1
                        }.forEach { recipe ->
                            item {
                                FavoriteRecipeListItem(
                                    recipe = recipe,
                                    favoriteRecipeIds = favoriteRecipes.map { it.id },
                                    onRecipeClicked = onRecipeClicked,
                                    onLiked = state::addFavoriteRecipe,
                                    onUnliked = state::removeFavoriteRecipe
                                )
                            }
                            item { Divider(color = Color.LightGray) }
                        }
                    }
                }
            }
            FavoriteTab.SelectMenuTab -> {
                LazyColumn {
//                    item { FavoriteRecipeList(state) }
                    favoriteMenus.forEach { menu ->
                        item {
                            MenuListItem(
                                menu = menu,
                                favoriteMenuIds = favoriteMenus.map { it.key },
                                onMenuClicked = onMenuClicked,
                                onLikeClicked = state::addFavoriteMenu,
                                onUnlikeClicked = state::removeFavoriteMenu
                            )
                        }
                        item { Divider(color = Color.LightGray) }
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
                modifier = Modifier.height(45.dp),
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
            val backgroundColor =
                if (selected) colorResource(id = R.color.categoryTabColor)
                else Color.White
            val borderColor =
                if (selected) colorResource(id = R.color.categoryTabColor)
                else colorResource(id = R.color.borderLightColor)

            Tab(
                modifier = modifier
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .border(border = BorderStroke(1.5.dp, borderColor), shape = CircleShape),
                selected = selected,
                selectedContentColor = Color.White,
                unselectedContentColor = colorResource(id = R.color.categoryTabColor),
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    modifier = modifier.padding(vertical = 8.dp),
                    text = stringResource(id = item.titleId),
                    fontSize = 16.sp,
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
fun FavoriteRecipeListItem(
    recipe: RecipeWithCategory,
    favoriteRecipeIds: List<Int>,
    onRecipeClicked: (Int, String) -> Unit,
    onLiked: (Int) -> Unit,
    onUnliked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onRecipeClicked(recipe.id, recipe.image) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box( // ->image
                modifier = Modifier
                    .size(115.dp, 75.dp)
                    .background(color = Color.LightGray)
            ) {
                AsyncImage(model = recipe.image, contentDescription = null)
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = recipe.title,
                fontSize = 18.sp,
                color = colorResource(id = R.color.fontColor)
            )
        }
        Icon(
            Icons.Sharp.Favorite,
            contentDescription = "favorite",
            modifier = Modifier
                .size(30.dp)
                .align(alignment = Alignment.Bottom)
                .clickable {
                    if (favoriteRecipeIds.contains(recipe.id)) onUnliked(recipe.id)
                    else onLiked(recipe.id)
                },
            tint = if (favoriteRecipeIds.contains(recipe.id))
                colorResource(id = R.color.favoriteIconColor)
            else Color.LightGray
        )
    }
}