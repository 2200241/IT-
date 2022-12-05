package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
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
import com.example.recipe_app.R
import com.example.recipe_app.make_menu.select_conditions.rippleClickable
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun ShoppingList(
    state: ShoppingListState = rememberShoppingListState(),
    paddingValues: PaddingValues,
    onThumbClicked: (String, String) -> Unit
) {
    val uiState = state.uiState

    Column(modifier = Modifier.padding(paddingValues)) {
        // TODO
        SelectedRecipes(false, uiState.recipes, onThumbClicked)
        Divider(color = Color.LightGray)

        LazyColumn() {
            item {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 10.dp),
                    text = "材料",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.fontColor)
                )
            }
            item { ShoppingListMaterials() }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            backgroundColor = Color.White,
            contentColor = Color.LightGray,
            onClick = { /*TODO*/ }
        ) {
            Icon(
                Icons.Sharp.Favorite,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ShoppingListMaterials() {
    for (i in 1..15) {
        val checkedState = remember { mutableStateOf(false) }
        Column(modifier = Modifier.padding(horizontal = 15.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .rippleClickable { checkedState.value = !checkedState.value },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.size(40.dp),
                    colors = CheckboxDefaults.colors(colorResource(id = R.color.selectButtonColor)),
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )
                Text(
                    text = "材料$i",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.fontColor)
                )
            }
            Divider(
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.LightGray
            )
        }
    }
    Spacer(Modifier.height(100.dp))
}