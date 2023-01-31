package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.recipe_app.R
import com.example.recipe_app.data.MenuWithRecipeThumbs
import com.example.recipe_app.data.RecipeWithoutCategory
import com.example.recipe_app.data.ShoppingItem
import com.example.recipe_app.data.ShoppingItemWithIngredient
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun ShoppingList(
    state: ShoppingListState = rememberShoppingListState(),
    paddingValues: PaddingValues,
    onThumbClicked: (Int, String) -> Unit
) {
    val uiState = state.uiState
    val menuDetail = state.menuDetail
    val favoriteMenuIds = state.favoriteMenuIds
    val onDismissRequest = remember { mutableStateOf(false) }

    if (onDismissRequest.value) {
        MyDialog(onDismissRequest = { onDismissRequest.value = it })
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally),
            text = "買い物リスト",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.fontColor)
        )
        Divider(color = Color.LightGray)
        LazyColumn {
            item { SelectedRecipes(false, menuDetail.map { it.key }, onThumbClicked) }
            item { Divider(color = Color.LightGray) }
/*
            item {
                */
/*Text(
                    modifier = Modifier.padding(start = 15.dp, top = 15.dp),
                    text = "材料(人分)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.fontColor)
                )*//*

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "材料(人分)",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.fontColor)
                    )
                    TextButton(
                        onClick = { onDismissRequest.value = true }
                    ) {
                        Text(
                            text = "人数変更",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
*/
            item { ShoppingListMaterials(menuDetail.values.map { it }, state::checkShoppingListItem) }
            item { Spacer(Modifier.height(80.dp)) }
        }
    }

    ShoppingListBottomButton(
        paddingValues = paddingValues,
        menu = menuDetail,
        favoriteMenuIds = favoriteMenuIds,
        onMenuLiked = state::addFavorite,
        onMenuUnLiked = state::removeFavorite
    )
}

@Composable
fun SettingNumber() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Sharp.Remove,
            contentDescription = "Remove",
            modifier = Modifier
                .clickable { /*TODO*/ }
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(50),
                )
                .border(BorderStroke(1.dp, Color.Gray))
        )
        Spacer(Modifier.width(15.dp))
        Text(
            text = "人分",
            fontSize = 18.sp,
            color = colorResource(id = R.color.fontColor)
        )
        Spacer(Modifier.width(10.dp))
        Icon(
            Icons.Sharp.Add,
            contentDescription = "Add",
            modifier = Modifier
                .clickable { /*TODO*/ }
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(50),
                )
                .border(BorderStroke(1.dp, Color.Gray))
        )
    }
}

@Composable
fun MyDialog(
    onDismissRequest: (Boolean) -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissRequest(false) },
        content = {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "人数設定",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.fontColor)
                        )
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "Cancel",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { onDismissRequest(false) }
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    SettingNumber()
                    Spacer(Modifier.height(10.dp))
                    Box(
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        TextButton(
                            onClick = { onDismissRequest(false) },
                        ) {
                            Text(
                                text = "保存",
                                fontSize = 18.sp,
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ShoppingListMaterials(
    items: List<ShoppingItem>,
    onChecked: (Int) -> Unit
) {
    items.forEachIndexed { index, item ->
//        val checkedState = remember { mutableStateOf(false) }
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 5.dp)
                    .fillMaxWidth()
                    .clickable { onChecked(index) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier
                        .absoluteOffset((-8).dp, 0.dp)
                        .size(40.dp),
                    colors = CheckboxDefaults.colors(colorResource(id = R.color.selectButtonColor)),
                    checked = item.isChecked,
                    onCheckedChange = {}
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.absoluteOffset((-8).dp, 0.dp),
                        text = item.ingredient.name,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.fontColor)
                    )
                    Text(
                        text = item.ingredient.quantity,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.fontColor)
                    )
                }
            }
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun ShoppingListBottomButton(
    paddingValues: PaddingValues,
    menu: Map.Entry<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>,
    favoriteMenuIds: List<Int>,
    onMenuLiked: (Int) -> Unit,
    onMenuUnLiked: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(end = 12.dp, bottom = 8.dp),
            backgroundColor = Color.White,
            contentColor = Color.LightGray,
            onClick = {
                if (favoriteMenuIds.contains(menu.id))
                    onMenuUnLiked(menu.id)
                else onMenuLiked(menu)
            }
        ) {
            Icon(
                imageVector = Icons.Sharp.Favorite,
                contentDescription = "favorite",
                tint = if (favoriteMenuIds.contains(menu.id))
                    colorResource(id = R.color.favoriteIconColor)
                else Color.LightGray
            )
        }
    }
}