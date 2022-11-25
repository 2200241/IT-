package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        largeCategories.forEach { item ->
            TagCategory(
                largeCategory = item,
                selectedTags = selectedTags,
                onTagClicked = onTagClicked
            )
        }
    }
}

@Composable
fun TagCategory(
    largeCategory: LargeCategory,
    selectedTags: List<Int>,
    onTagClicked: (Int) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Title(largeCategory.id)
        SelectTagsButton(
            items = largeCategory.items,
            selectedTags = selectedTags,
            onTagClicked = onTagClicked
        )
    }
}

@Composable
fun Title(id: Int) {
    Text(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp),
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
            val backgroundColor = if (selectedTags.contains(item)) colorResource(id = R.color.buttonColor) else Color.White
            val contentColor = if (selectedTags.contains(item)) Color.White else colorResource(id = R.color.fontColor)
            val fontWeight = if (selectedTags.contains(item)) FontWeight.Bold else FontWeight.Normal
            OutlinedButton(
                border = BorderStroke(1.5.dp, colorResource(id = R.color.borderLightColor)),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundColor, contentColor = contentColor),
                onClick = { onTagClicked(item) }
            ) {
                Text(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(item),
                    fontSize = 18.sp,
                    fontWeight = fontWeight
                )
            }
        }
    }
}