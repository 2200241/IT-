package com.example.recipe_app.make_menu.select_conditions

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recipe_app.R
import androidx.compose.ui.unit.sp
import com.example.recipe_app.ui.theme.Shapes

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

        BottomButtons(onSearchClicked = { onSearchClicked(state.getConditions()) })
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
        backgroundColor = Color.White
    ) {
        ConditionTabs.forEach { item ->
            Tab(
                modifier = Modifier.height(50.dp),
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
fun BottomButtonLayout(content: @Composable RowScope.()->Unit) {
    Row { content() }
}

@Composable
fun BottomButtons(onSearchClicked: () -> Unit) {
    BottomButtonLayout {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier.weight(1f),
                backgroundColor = colorResource(id = R.color.clearButtonColor),
                contentColor = Color.White,
                text = {
                    Text(
                        text = "クリア",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = { Log.d("Button", "onClick") }
            )
            Spacer(modifier = Modifier.width(15.dp))
            ExtendedFloatingActionButton(
                modifier = Modifier.weight(1f),
                backgroundColor = colorResource(id = R.color.searchButtonColor),
                contentColor = Color.White,
                text = {
                    Text(
                        text = "検索",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                onClick = onSearchClicked
            )
        }
    }
}