package com.example.recipe_app.menu_list.select_menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.data.MenuWithoutIngredients
// 上書き防止
@Composable
fun SelectMenu(
    state: SelectMenuState = rememberSelectMenuState(),
    paddingValues: PaddingValues,
    onItemClicked: (Int) -> Unit
) {
    val uiState = state.uiState

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            text = stringResource(id = R.string.menuList_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.fontColor)
        )

        LazyColumn() {
            item {
                state.menus.forEach { menu ->
                    Divider(color = Color.LightGray)
                    MenuListItem(
                        menu = menu,
                        favoriteMenus = state.favoriteMenus,
                        onMenuClicked = onItemClicked,
                        onLikeClicked = state::addFavoriteMenu,
                        onUnlikeClicked = state::removeFavoriteMenu
                    )
                    Divider(color = Color.LightGray)
                }
            }
        }
    }
}

@Composable
fun MenuListItem(
    menu: MenuWithoutIngredients,
    favoriteMenus: List<MenuWithoutIngredients>,
    onMenuClicked: (Int) -> Unit,
    onLikeClicked: (MenuWithoutIngredients) -> Unit,
    onUnlikeClicked: (id: Int) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.width((screenWidth-60).dp)
        ) {
            LazyRow(
                modifier = Modifier.clickable {onMenuClicked(menu.id) },
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                menu.recipes.forEach { recipe ->
                    item {
                        Box(
                            modifier = Modifier
                                .size(110.dp, 75.dp)
                                .background(color = Color.LightGray)
                        ) {
                            Text(text = "料理画像", color = Color.White)
                        }
                    }
                }
            }
        }
        Icon(
            Icons.Sharp.Favorite,
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 12.dp, end = 12.dp)
                .size(30.dp)
                .align(alignment = Alignment.Bottom)
                .clickable {
                    if (favoriteMenus.contains(menu)) onUnlikeClicked(menu.id)
                    else onLikeClicked(menu)
                },
            tint = if (favoriteMenus.contains(menu))
                colorResource(id = R.color.favoriteIconColor)
            else Color.Gray
        )
    }
}