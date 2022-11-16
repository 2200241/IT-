package com.example.recipe_app.menu_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.favorite_list.FavoriteListItems

@Composable
fun MenuListScreen() {
    LazyColumn(/*modifier = Modifier.padding(10.dp)*/) {
        item {
            for (i in 1..10) {
                MenuListItems()
                Divider(color = Color.Gray)
            }
        }
        //item { ShoppingList() }
    }
}

@Composable
fun MenuListItems() {
    LazyRow(
        modifier = Modifier.clickable {  },
        contentPadding = PaddingValues(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 1..5) {
            item {
                Box(
                    modifier = Modifier
                        .size(110.dp, 75.dp)
                        .background(color = Color.LightGray)
                ) {
                    Text(
                        text = "料理画像",
                        color = Color.White,
                    )
                }
            }
        }
    }
    /*IconButton(
        onClick = { Log.d("Button", "onClick") }
    ) {
        Icon(
            Icons.Sharp.Star,
            contentDescription = null,
            tint = Color.LightGray
        )
    }*/
}

@Composable
fun ShoppingList() {
    Column() {
        MenuListItems()
        Divider(color = Color.LightGray)
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 15.dp),
            text = "材料",
            fontSize = 20.sp,
        )
        Column(modifier = Modifier.padding(20.dp)) {
            for (i in 1..5) {
                val checkedState = remember { mutableStateOf(false) }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        modifier = Modifier.size(32.dp),
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                    Text(
                        text = "材料名$i",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                }
            }
        }
    }
}



