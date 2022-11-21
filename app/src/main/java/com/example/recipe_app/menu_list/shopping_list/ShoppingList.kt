package com.example.recipe_app.menu_list.shopping_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun ShoppingList(
    state: ShoppingListState
) {
    val uiState = state.uiState

    Column() {
        SelectedRecipes()
        Divider(color = Color.LightGray)
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
            text = "材料",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        for (i in 1..10) {
            val checkedState = remember { mutableStateOf(false) }
            Column(/*modifier = Modifier.padding(vertical = 10.dp)*/) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        modifier = Modifier.size(32.dp),
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = "材料名$i",
                        fontSize = 18.sp,
                        color = Color(0xFF333333)
                    )
                }
                Divider(color = Color.LightGray)
                Spacer(Modifier.width(10.dp))
            }
        }
    }
}