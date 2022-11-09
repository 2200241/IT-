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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SelectTags(
    modifier: Modifier = Modifier,
    tagList: List<Int> = emptyList(),
    selectedTags: List<Int> = emptyList(),
    onTagClicked: (Int) -> Unit = {},
    onSearchClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 1..10) {
            Title("Title$i")
            SelectTagsButton()
        }
    }
}

@Composable
fun Title(text: String) {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = text, fontSize = 20.sp)
        Divider(
            color = colorResource(id = R.color.gray),
            thickness = 1.dp
        )
    }
}

@Composable
fun SelectTagsButton(/*items: List<String>*/) {
    FlowRow(
        mainAxisSpacing = 12.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        for (i in 1..6) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = { Log.d("Button", "onClick") }
            ) {
                Text(
                    text = "tag$i",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}