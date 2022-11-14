package com.example.recipe_app.favorite_list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteListScreen() {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            item {
                for (i in 1..10) {
                    FavoriteList("料理名$i")
                    Divider(color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun FavoriteList(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { Log.d("favoriteListItem", "onClick") },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Box( // ->image
                modifier = Modifier
                    .size(100.dp, 60.dp)
                    .background(color = Color.Gray)
            ) {
                Text(
                    text = "料理画像",
                    color = Color.White
                )
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = title,
                color = Color.DarkGray
            )
        }
        IconButton(
            onClick = { Log.d("Button", "onClick") }
        ) {
            Icon(
                Icons.Sharp.Star,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}