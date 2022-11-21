package com.example.recipe_app.menu_list.select_menu

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun SelectMenu(
    paddingValues: PaddingValues,
    onItemClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            for (i in 1..10) {
                MenuListItem(onItemClicked = onItemClicked)
                Divider(color = Color.Gray)
            }
        }
    }
}

@Composable
fun MenuListItem(
    onItemClicked: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.size((screenWidth-60).dp, 100.dp)
        ) {
            LazyRow(
                modifier = Modifier.clickable { onItemClicked("testId") },
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