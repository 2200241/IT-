package com.example.recipe_app.menu_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.favorite_list.FavoriteListItems
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun MenuListScreen() {
    LazyColumn() {
        item {
            for (i in 1..10) {
                MenuListItem()
                Divider(color = Color.Gray)
            }
        }
    }
}

@Composable
fun MenuListItem() {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.size((screenWidth-60).dp, 100.dp)
        ) {
            LazyRow(
                modifier = Modifier.clickable {  },
                contentPadding = PaddingValues(all = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (i in 1..5) {
                    item {
                        Box(
                            modifier = Modifier
                                .size(100.dp, 80.dp)
                                .background(color = Color.LightGray)
                        ) {
                            Text(text = "料理画像", color = Color.White)
                        }
                    }
                }
            }
        }
        IconButton(
            modifier = Modifier
                .fillMaxSize()
                .size(100.dp),
                //.align(alignment = Alignment.Bottom),
            onClick = { Log.d("Button", "onClick") }
        ) {
            Icon(
                Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun ShoppingList() {
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



