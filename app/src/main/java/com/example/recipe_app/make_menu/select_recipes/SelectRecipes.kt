package com.example.recipe_app.make_menu.select_recipes

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectRecipes(
    state: SelectRecipesState,
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    LazyColumn() {
        item { Row() {
            val testId = (0..10000).random()
            TextButton(onClick = { onItemClicked(testId.toString()) }) {
                Text(text = state.uiState.testString)
            }
        } }
        //item { SelectedRecipe() }
    }
}

@Composable
fun SelectedRecipe() {
    LazyRow(
        contentPadding = PaddingValues(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            for (i in 1..5) {
                Text(
                    text = "料理画像",
                    color = Color.White,
                    modifier = Modifier
                        .background(
                            color = Color.Gray
                        )
                        .padding(all = 8.dp)
                        .size(width = 100.dp, height = 40.dp)
                )
            }
        }
    }
}