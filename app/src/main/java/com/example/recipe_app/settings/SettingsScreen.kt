package com.example.recipe_app.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .align(Alignment.CenterHorizontally),
            text = "設定",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.fontColor)
        )
        Divider(color = Color.LightGray)
        Text(
            modifier = Modifier.padding(start = 15.dp, top = 15.dp),
            text = "アレルギー設定",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.fontColor)
        )
        Allergies()
    }
}

@Composable
fun Allergies() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(3.dp)
    ) {
        val allergyList = listOf(
            "卵", "乳", "小麦", "そば", "落花生", "えび", "かに", "アーモンド",
            "あわび", "いか", "いくら", "オレンジ", "カシューナッツ", "キウイ", "牛肉",
            "くるみ", "ごま", "さけ", "さば", "鶏肉", "バナナ", "豚肉", "まつたけ",
            "桃", "やまいも", "りんご", "ゼラチン"
        )

        //for (i in 1..15) {
        allergyList.forEach {  item ->
            item {
                val checkedState = remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier.clickable { checkedState.value = !checkedState.value },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        modifier = Modifier.size(40.dp),
                        colors = CheckboxDefaults.colors(colorResource(id = R.color.selectButtonColor)),
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                    Text(
                        //text = "アレルギー",
                        text = item,
                        //fontSize = 16.sp,
                        fontSize = if(item.length > 5) 13.sp else 16.sp,
                        color = colorResource(id = R.color.fontColor),
                    )
                }
            }
        }
    }
}