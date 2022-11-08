package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role

@Composable
fun SelectTags(
    tagList: List<Int> = emptyList(),
    selectedTags: List<Int> = emptyList(),
    onTagClicked: (Int) -> Unit = {},
    onSearchClicked: () -> Unit = {}
) {
    Column() {
        Text("Tags")
        Button(onClick = onSearchClicked) {

        }
    }
    LazyColumn() {
        items(tagList) { item ->
            Row(
                Modifier
                    .toggleable(
                        value = selectedTags.contains(item),
                        role = Role.Checkbox,
                        onValueChange = { onTagClicked(item) }
                    )
                    .fillMaxWidth()
            ) {
                Checkbox(
                    checked = selectedTags.contains(item),
                    onCheckedChange = null
                )
                Text(text = stringResource(item))
            }
        }
    }
}