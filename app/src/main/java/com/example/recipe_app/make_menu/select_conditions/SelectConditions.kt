package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.recipe_app.make_menu.select_conditions.select_ingredients.SelectIngredients
import com.example.recipe_app.make_menu.select_conditions.select_tags.SelectTags

@Composable
fun SelectConditions(
    state: SelectConditionsState = rememberSelectConditionsState(),
    onSearchClicked: () -> Unit = {},
) {
    val uiState = state.uiState

    Column() {
        SelectConditionsTabBar(
            selectedTab = uiState.selectedTab,
            onClick = state::onTabClicked
        )

        when (uiState.selectedTab) {
            ConditionTab.SelectTagsTab -> {
                SelectTags(
                    onSearchClicked = onSearchClicked
                )
            }
            ConditionTab.SelectIngredientsTab -> {
                SelectIngredients(
                    onSearchClicked = onSearchClicked
                )
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