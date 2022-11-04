package com.example.recipe_app.make_menu.select_recipes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SelectRecipes(
    onItemClicked: (String) -> Unit = {},
    onBackPressed: () -> Unit = {}
) {
    LazyColumn() {
        item { Row() {
            val testId = (0..10000).random()
            TextButton(onClick = { onItemClicked(testId.toString()) }) {
                Text(text = "TEST")
            }
        } }
    }
}