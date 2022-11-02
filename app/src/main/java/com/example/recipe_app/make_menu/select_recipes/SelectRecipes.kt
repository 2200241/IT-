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
            TextButton(onClick = { onItemClicked("testId") }) {
                Text(text = "TEST")
            }
        } }
    }
}