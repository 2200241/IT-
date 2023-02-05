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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipe_app.R
import com.example.recipe_app.data.Allergen

@Composable
fun SettingsScreen(
    state: SettingsScreenState = rememberSettingsState(),
    paddingValues: PaddingValues,
) {
    val uiState = state.uiState
    val allergens = state.allergens

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
        Allergies(allergens, state::checkAllergen)
    }
}

@Composable
fun Allergies(
    allergens: List<Allergen> = emptyList(),
    check: (Int, Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp)
    ) {
        for (allergen in allergens) {
            item {
                Row(
                    modifier = Modifier.clickable { check(allergen.id, !allergen.isChecked) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        modifier = Modifier.size(40.dp),
                        colors = CheckboxDefaults.colors(colorResource(id = R.color.selectButtonColor)),
                        checked = allergen.isChecked,
                        onCheckedChange = { check(allergen.id, !allergen.isChecked) }
                    )
                    Text(
                        text = allergen.name,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.fontColor),
                    )
                }
            }
        }
    }
}