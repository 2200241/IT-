package com.example.recipe_app.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R

@Composable
fun RecipeDetail(
    state: RecipeDetailState = rememberRecipeDetailState(),
    paddingValues: PaddingValues,
    onBackPressed: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(paddingValues),
    ) {
        LazyColumn() {
            item { Header(state) }
            item { RecipeDetailTitle("材料") }
            item { RecipeDetailMaterials() }
            item { RecipeDetailTitle("作り方") }
            item { CookingProcedure() }
        }
    }

    RecipeDetailBottomButtons(paddingValues)
}

@Composable
fun Header(state: RecipeDetailState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = state.uiState.title,
            fontSize = 18.sp,
            color = colorResource(id = R.color.fontColor)
        )
        state.test()
        Box( // ->image
            modifier = Modifier
                .size(160.dp, 115.dp)
                .background(color = Color.LightGray)
        ) {
            Text(text = "料理画像", color = Color.White)
        }
    }
}

@Composable
fun RecipeDetailTitle(title: String) {
    Text(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.fontColor)
    )
}

@Composable
fun RecipeDetailMaterials() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        for (i in 1..10) {
            Text(
                text = "材料名$i",
                fontSize = 20.sp,
                color = colorResource(id = R.color.fontColor)
            )
            Divider(
                modifier = Modifier.padding(bottom = 10.dp),
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun CookingProcedure() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        for (i in 1..5) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = "$i.",
                fontSize = 20.sp,
                color = colorResource(id = R.color.fontColor)
            )
        }
    }
    Spacer(Modifier.height(100.dp))
}

@Composable
fun RecipeDetailBottomButtons(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExtendedFloatingActionButton(
                backgroundColor = colorResource(id = R.color.addListButtonColor),
                contentColor = Color.White ,
                text = {
                    Text(
                        text = "買い物リストへ追加",
                        fontSize = 18.sp
                    )
                },
                onClick = {}
            )
            FloatingActionButton(
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
}