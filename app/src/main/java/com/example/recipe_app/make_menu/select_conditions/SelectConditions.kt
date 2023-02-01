package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
                    modifier = Modifier.weight(1f),
                    keywords = uiState.keywords,
                    setKeywords = state::setKeywords
                )
            }
        }

        SelectConditionsBottomButtons(
            onSearchClicked = { onSearchClicked(state.getConditions()) }
        )
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
    ) {
        ConditionTabs.forEach { item ->
            Tab(
                modifier = Modifier.height(45.dp),
                selected = item.index == selectedTab.index,
                selectedContentColor = colorResource(id = R.color.fontColor),
                unselectedContentColor = Color.Gray,
                onClick = {
                    onClick(item)
                }
            ) {
                Text(
                    text = stringResource(id = item.titleId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
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
fun SelectConditionsBottomButtons(onSearchClicked: () -> Unit) {
    ExtendedFloatingActionButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 8.dp),
        backgroundColor = colorResource(id = R.color.searchButtonColor),
        contentColor = Color.White,
        text = {
            Text(
                text = stringResource(id = R.string.search),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        onClick = onSearchClicked
    )
}

/*@Composable
fun SelectConditionsBottomButtons(onSearchClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier.weight(1f),
            backgroundColor = colorResource(id = R.color.clearButtonColor),
            contentColor = Color.White,
            text = {
                Text(
                    text = stringResource(id = R.string.clear),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            onClick = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.width(15.dp))
        ExtendedFloatingActionButton(
            modifier = Modifier.weight(1f),
            backgroundColor = colorResource(id = R.color.searchButtonColor),
            contentColor = Color.White,
            text = {
                Text(
                    text = stringResource(id = R.string.search),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            onClick = onSearchClicked
        )
    }
}*/