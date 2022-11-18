package com.example.recipe_app.recipe_detail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R

@Composable
fun RecipeDetail(
    state: RecipeDetailState,
    onBackPressed: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 15.dp),
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = state.uiState.title, fontSize = 18.sp) // 料理名
                state.test()
                Box( // ->image
                    modifier = Modifier
                        .size(120.dp, 80.dp)
                        .background(color = Color.LightGray)
                ) {
                    Text(
                        text = "料理画像",
                        color = Color.White
                    )
                }
            }
        }
        item { Material() }
        item { CookingProcedure() }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ExtendedFloatingActionButton(
                    text = {
                        Text(
                            text = "買い物リストへ追加",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    },
                    onClick = {}
                )
                IconButton(
                    //modifier = Modifier.size(150.dp),
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
    }
}

@Composable
fun Material() {
    Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
        Text(
            text = "材料",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        for (i in 1..5) {
            Text(
                text = "材料名$i",
                fontSize = 20.sp,
                color = Color(0xFF333333)
            )
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun CookingProcedure() {
    Column(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
        Text(
            text = "作り方",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        for (i in 1..5) {
            Text(
                text = "$i. 手順$i",
                fontSize = 20.sp,
                color = Color(0xFF333333)
            )
        }
    }
}