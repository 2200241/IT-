package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun SelectTags(
    tagList: List<Int> = emptyList(),
    onSearchClicked: () -> Unit = {}
) {
    Column() {
        Text("Tags")
        Button(onClick = onSearchClicked) {

        }
    }
    LazyColumn() {
        items(tagList) { item ->
            Row() {
                Checkbox(checked = false, onCheckedChange = {})
                Text(text = stringResource(item))
            }
        }
    }
}