package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SelectIngredients(
    onSearchClicked: () -> Unit = {}
) {
    Column() {
        Text("Ingredients")
        Button(onClick = onSearchClicked) {

        }
    }
}