package com.example.recipe_app.make_menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.recipe_app.make_menu.select_conditions.SelectConditions

@Composable
fun MakeMenuScreen(
    state: MakeMenuScreenState = rememberMakeMenuScreenState()
) {
    val uiState = state.uiState

    Column {
        SelectConditions()
    }
}