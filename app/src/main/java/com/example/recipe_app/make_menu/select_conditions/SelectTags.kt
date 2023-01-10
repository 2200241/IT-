package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
    LazyColumn(modifier = modifier) {
        largeCategories.forEach { item ->
            item {
                TagCategory(
                    largeCategory = item,
                    selectedTags = selectedTags,
                    onTagClicked = onTagClicked
                )
            }
        }
    }
}

@Composable
fun TagCategory(
    largeCategory: LargeCategory,
    selectedTags: List<Int>,
    onTagClicked: (Int) -> Unit
) {
    SelectTagsTitle(largeCategory.id)
    SelectTagsButton(
        items = largeCategory.items,
        selectedTags = selectedTags,
        onTagClicked = onTagClicked
    )
}

@Composable
fun SelectTagsTitle(id: Int) {
    Text(
        modifier = Modifier.padding(start = 20.dp, top = 15.dp, bottom = 3.dp),
        text = stringResource(id),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = colorResource(id = R.color.fontColor)
    )
}

@Composable
fun SelectTagsButton(
    items: List<Int>,
    selectedTags: List<Int>,
    onTagClicked: (Int) -> Unit
) {
    FlowRow(
        modifier = Modifier.padding(start = 20.dp),
        mainAxisSpacing = 12.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        items.forEach { item ->
            val backgroundColor = if (selectedTags.contains(item)) colorResource(id = R.color.selectButtonColor) else Color.White
            val contentColor = if (selectedTags.contains(item)) Color.White else colorResource(id = R.color.fontColor)
            val borderColor = if (selectedTags.contains(item)) colorResource(id = R.color.selectButtonColor) else colorResource(id = R.color.borderLightColor)
            val fontWeight = if (selectedTags.contains(item)) FontWeight.Bold else FontWeight.Normal

            OutlinedButton(
                border = BorderStroke(1.5.dp, borderColor),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundColor, contentColor = contentColor),
                onClick = { onTagClicked(item) }
            ) {
                Text(
                    text = stringResource(item),
                    fontSize = 16.sp,
                    fontWeight = fontWeight
                )
            }
        }
    }
}