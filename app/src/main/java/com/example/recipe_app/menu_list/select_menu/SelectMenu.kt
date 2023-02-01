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
import coil.compose.AsyncImage
import com.example.recipe_app.R
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.RecipeWithoutCategory

@Composable
fun SelectMenu(
    state: SelectMenuState = rememberSelectMenuState(),
    paddingValues: PaddingValues,
    onItemClicked: (Int) -> Unit
) {
    val uiState = state.uiState

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.menuList_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.fontColor)
        )
        Divider(color = Color.LightGray)
        LazyColumn {
            item {
                state.menus.forEach { menu ->
                    MenuListItem(
                        menu = menu,
                        favoriteMenuIds = state.favoriteMenuIds,
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
    menu: Map.Entry<Menu, List<RecipeWithoutCategory>>,
    favoriteMenuIds: List<Int>,
    onMenuClicked: (Int) -> Unit,
    onLikeClicked: (Int) -> Unit,
    onUnlikeClicked: (Int) -> Unit
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
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onMenuClicked(menu.key.id) },
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                menu.value.forEach { recipe ->
                    item {
                        Box(
                            modifier = Modifier
                                .size(110.dp, 75.dp)
                                .background(color = Color.LightGray)
                        ) {
//                            Text(text = "料理画像", color = Color.White)
                            AsyncImage(model = recipe.image, contentDescription = null)
                        }
                    }
                }
            }
        }
        Icon(
            imageVector = Icons.Sharp.Favorite,
            contentDescription = "favorite",
            modifier = Modifier
                .padding(bottom = 12.dp, end = 12.dp)
                .size(30.dp)
                .align(alignment = Alignment.Bottom)
                .clickable {
                    if (favoriteMenuIds.contains(menu.key.id)) onUnlikeClicked(menu.key.id)
                    else onLikeClicked(menu.key.id)
                },
            tint = if (favoriteMenuIds.contains(menu.key.id))
                colorResource(id = R.color.favoriteIconColor)
            else Color.LightGray
        )
    }
}