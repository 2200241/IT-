package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SelectConditions(
    padding: PaddingValues = PaddingValues(),
    state: SelectConditionsState = rememberSelectConditionsState(),
    onSearchClicked: () -> Unit = {},
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
                    tagList = uiState.tagList,
                    selectedTags = uiState.selectedTags,
                    onTagClicked = state::onTagClicked,
                    onSearchClicked = onSearchClicked
                )
            }
            ConditionTab.SelectIngredientsTab -> {
                SelectIngredients(
                    modifier = Modifier.weight(1f),
                    onSearchClicked = onSearchClicked
                )
            }
        }

        Row(
        ) {
            ClearButton()
            SearchButton {
            }
        }
    }
}

@Composable
private fun SelectConditionsTabBar(
    modifier: Modifier = Modifier,
    selectedTab: ConditionTab,
    onClick: (ConditionTab) -> Unit = {}
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.index
    ) {
        ConditionTabs.forEach { item ->
            Tab(
                selected = item.index == selectedTab.index,
                onClick = {
                    onClick(item)
                }
            ) {
                Text(text = stringResource(id = item.titleId))
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
                Text(
                    text = "検索",
                )
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
            text = { Text("クリア") }
        )
    }
}