package com.example.recipe_app.favorite_list.select_favorite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material.icons.sharp.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R

@Composable
fun SelectFavorite(
    state: SelectFavoriteState = rememberSelectFavoriteState(),
    paddingValues: PaddingValues
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            for (i in 1..10) {
                FavoriteListItems("料理名$i")
                Divider(color = Color.LightGray)
            }
        }
    }
}

@Composable
fun FavoriteListItems(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { Log.d("favoriteListItem", "onClick") },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row() {
            Box( // ->image
                modifier = Modifier
                    .size(115.dp, 75.dp)
                    .background(color = Color.LightGray)
            ) {
                Text(text = "料理画像", color = Color.White)
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = title,
                fontSize = 18.sp,
                color = colorResource(id = R.color.fontColor)
            )
        }
        Icon(
            Icons.Sharp.Favorite,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .align(alignment = Alignment.Bottom)
                .clickable {  },
            tint = colorResource(id = R.color.favoriteIconColor)
        )
    }
}