package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipe_app.R
import androidx.compose.ui.unit.sp

@Composable
fun SelectConditions(
    state: SelectConditionsState = rememberSelectConditionsState(),
    padding: PaddingValues,
    onSearchClicked: (String) -> Unit,
) {
    val uiState = state.uiState

    Column(
        modifier = Modifier.padding(padding)
    ) {
        SelectConditionsTabBar(
            selectedTab = uiState.selectedTab,
            onClick = state::onTabClicked
        )

        when (uiState.selectedTab) {
            ConditionTab.SelectTagsTab -> {
                SelectTags(
                    modifier = Modifier.weight(1f),
                    largeCategories = uiState.largeCategories,
                    selectedTags = uiState.selectedTags,
                    onTagClicked = state::onTagClicked
                )
            }
            ConditionTab.SelectIngredientsTab -> {
                SelectIngredients(
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Row(
        ) {
            ClearButton()
            SearchButton(onSearchClicked = { onSearchClicked(state.getConditions()) })
        }
    }
}

@Composable
private fun SelectConditionsTabBar(
    modifier: Modifier = Modifier,
    selectedTab: ConditionTab,
    onClick: (ConditionTab) -> Unit
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.index,
        backgroundColor = Color.White,
        contentColor = Color(0xFF333333)
    ) {
        ConditionTabs.forEach { item ->
            Tab(
                modifier = Modifier.height(50.dp),
                selected = item.index == selectedTab.index,
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    text = stringResource(id = item.titleId),
                    fontSize = 20.sp,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}

private val ConditionTabs = listOf(
    ConditionTab.SelectTagsTab,
    ConditionTab.SelectIngredientsTab
)

@Composable
fun SearchButton(onSearchClicked: () -> Unit) {
    Box(/*modifier = Modifier.fillMaxSize()*/) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = onSearchClicked, // →レシピ一覧画面(→レシピ画面)
            text = {
                Text(text = stringResource(R.string.search))
            }
        )
    }
}

@Composable
fun ClearButton() {
    Box(/*modifier = Modifier.fillMaxSize()*/) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = { Log.d("Button", "onClick") },
            text = { Text(stringResource(R.string.clear)) }
        )
    }
}