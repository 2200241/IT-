package com.example.recipe_app.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.make_menu.select_conditions.rippleClickable
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "アレルギー登録",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.fontColor)
            )
            Divider(color = Color.LightGray)
        }
        Allergies()
    }
}
@Composable
fun Allergies() {
    FlowRow(
        modifier = Modifier.padding(horizontal = 5.dp),
        mainAxisSpacing = 12.dp,
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisSpacing = 5.dp,
    ) {
        for (i in 1..15) {
            val checkedState = remember { mutableStateOf(false) }
            Row(
                modifier = Modifier.rippleClickable { checkedState.value = !checkedState.value },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.size(40.dp),
                    colors = CheckboxDefaults.colors(colorResource(id = R.color.selectButtonColor)),
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )
                Text(
                    text = "アレルギー$i",
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.fontColor)
                )
            }
        }
    }
}
