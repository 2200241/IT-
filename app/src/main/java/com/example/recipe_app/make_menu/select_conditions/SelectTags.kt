package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SelectTags(
    modifier: Modifier = Modifier,
    largeCategories: List<LargeCategory>,
    selectedTags: List<Int>,
    onTagClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        largeCategories.forEach { item ->
            TagCategory(
                largeCategory = item,
                selectedTags = selectedTags,
                onTagClicked = onTagClicked
            )
        }
/*
        for (i in 1..10) {
            Title("Title$i")
            SelectTagsButton()
        }
*/
    }
}

@Composable
fun TagCategory(
    largeCategory: LargeCategory,
    selectedTags: List<Int>,
    onTagClicked: (Int) -> Unit
) {
    Title(largeCategory.id)
    SelectTagsButton(
        items = largeCategory.items,
        selectedTags = selectedTags,
        onTagClicked = onTagClicked
    )
}

@Composable
fun Title(id: Int) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = stringResource(id), fontSize = 20.sp)
        Divider(
            color = colorResource(id = R.color.gray),
            thickness = 1.dp
        )
    }
}

@Composable
fun SelectTagsButton(
    items: List<Int>,
    selectedTags: List<Int>,
    onTagClicked: (Int) -> Unit
) {
    FlowRow(
        mainAxisSpacing = 12.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        items.forEach { item ->
            val backgroundColor = if (selectedTags.contains(item)) Color.Blue else Color.White
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                onClick = { onTagClicked(item) }
            ) {
                Text(
                    text = stringResource(item),
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}