package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectTags(
    onSearchClicked: () -> Unit = {}
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(
            modifier = Modifier.padding(20.dp),
        ) {
            /*Text("Tags")
            Button(onClick = onSearchClicked) {
            }*/
            item {
                for (i in 1..5) {
                    Column(modifier = Modifier.padding(bottom = 30.dp)) {
                        Title("タイトル名$i")
                        SelectTagsButton()
                    }
                }
            }
        }
    }
}

@Composable
fun Title(text: String) {
    Text(
        modifier = Modifier.padding(start = 5.dp),
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF333333)
    )
}

@Composable
fun SelectTagsButton() {
    FlowRow(
        mainAxisSpacing = 12.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        for (i in 1..6) {
            OutlinedButton(
                border = BorderStroke(1.5.dp, Color(0xFFE7E7E7)),
                //border = BorderStroke(1.5.dp, Color(0xFFE7D0A9)),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF333333),
                    //backgroundColor = Color(0xFFE7D0A9)
                ),
                onClick = { Log.d("Button", "onClick") }
            ) {
                Text(
                    text = "タグ名$i",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}