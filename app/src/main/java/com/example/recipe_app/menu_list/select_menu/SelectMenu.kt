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
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material.icons.sharp.StarBorder
import androidx.compose.material.icons.sharp.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.make_menu.select_recipes.SelectedRecipes

@Composable
fun SelectMenu(
    paddingValues: PaddingValues,
    onItemClicked: (String) -> Unit
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            text = stringResource(id = R.string.menuList_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.fontColor)
        )

        LazyColumn() {
            item {
                for (i in 1..10) {
                    Divider(color = Color.LightGray)
                    MenuListItem(onItemClicked = onItemClicked)
                    Divider(color = Color.LightGray)
                }
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
            modifier = Modifier.width((screenWidth-60).dp)
        ) {
            LazyRow(
                modifier = Modifier.clickable { onItemClicked("testId") },
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                for (i in 1..5) {
                    item {
                        Box(
                            modifier = Modifier
                                .size(110.dp, 75.dp)
                                .background(color = Color.LightGray)
                        ) {
                            Text(text = "料理画像", color = Color.White)
                        }
                    }
                }
            }
        }
        Icon(
            Icons.Sharp.Favorite,
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 12.dp, end = 12.dp)
                .size(30.dp)
                .align(alignment = Alignment.Bottom)
                .clickable { },
            tint = Color.LightGray
        )
    }
}