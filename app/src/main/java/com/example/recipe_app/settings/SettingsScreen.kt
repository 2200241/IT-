package com.example.recipe_app.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "アレルギー登録",
                fontSize = 20.sp,
                //fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
            Divider(color = Color.Gray)
        }
        AllergyList()
    }
}
@Composable
fun AllergyList() {
    FlowRow(
        mainAxisSpacing = 12.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        for (i in 1..15) {
            val checkedState = remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.clickable { checkedState.value = !checkedState.value },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.size(35.dp),
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )
                Text(
                    text = "アレルギー名$i",
                    fontSize = 18.sp,
                    //fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}
