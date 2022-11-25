package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun ShoppingList(
    state: ShoppingListState = rememberShoppingListState(),
    onThumbClicked: (String) -> Unit
) {
    val uiState = state.uiState

    LazyColumn() {
        item { SelectedRecipes(onThumbClicked) }
        item { Divider(color = Color.LightGray) }
        item {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 20.dp, bottom = 10.dp),
                text = "材料",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.fontColor)
            )
        }
        item {
            for (i in 1..10) {
                val checkedState = remember { mutableStateOf(false) }
                Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { checkedState.value = !checkedState.value },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            modifier = Modifier.size(40.dp),
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it }
                        )
                        Text(
                            text = "材料名$i",
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
        }
    }
}