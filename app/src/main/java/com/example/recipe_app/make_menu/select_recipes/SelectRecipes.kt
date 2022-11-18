package com.example.recipe_app.make_menu.select_recipes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SelectRecipes(
    state: SelectRecipesState,
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    LazyColumn(
    ) {
        /*item { Row() {
            val testId = (0..10000).random()
            TextButton(onClick = { onItemClicked(testId.toString()) }) {
                Text(text = state.uiState.testString)
            }
        } }*/
        item {
            Text(
                modifier = Modifier.padding(15.dp),
                text = "検索結果",
                fontSize = 18.sp,
                color = Color(0xFF333333)
            )
        }
        item { Divider(color = Color.LightGray) }
        item { SelectedRecipes() }
        item { Divider(color = Color.LightGray) }
        item { SearchResultRecipes(state, onItemClicked) }
    }
}

@Composable
fun SelectedRecipes() {
    LazyRow(
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
                    Text(text = "料理画像", color = Color.White)
                    FloatingActionButton(
                        modifier = Modifier
                            .size(18.dp)
                            .align(alignment = Alignment.TopEnd),
                        backgroundColor = Color(0xFF333333),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Sharp.Clear,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultRecipes(
    state: SelectRecipesState,
    onItemClicked: (String) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val testId = (0..10000).random()

    Box(modifier = Modifier.padding(10.dp)) {
        FlowRow(
            modifier = Modifier.padding(start = 10.dp),
            mainAxisSpacing = 12.dp,
            mainAxisAlignment = MainAxisAlignment.Center,
            crossAxisSpacing = 5.dp,
        ) {
            for (i in 1..10) {
                Box(
                    modifier = Modifier
                        //.padding(horizontal = 10.dp)
                        .size((screenWidth / 2 - 25).dp, 120.dp)
                        .background(color = Color.LightGray)
                        .clickable { onItemClicked(testId.toString()) }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            modifier = Modifier.align(alignment = Alignment.End),
                            onClick = { Log.d("Button", "onClick") }
                        ) {
                            Icon(
                                Icons.Sharp.Star,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = state.uiState.testString,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}