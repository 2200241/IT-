package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel

class SelectConditionsState(
    private val viewModel: SelectConditionsViewModel,
) {

    val uiState: SelectConditionsUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun onTabClicked(selectedTab: ConditionTab) = viewModel.onTabClicked(selectedTab)

    fun onTagClicked(id: Int) = viewModel.onTagClicked(id)

    fun setKeywords(text:String) = viewModel.setKeywords(text)

    fun getConditions() = viewModel.getConditions()
}

@Composable
fun rememberSelectConditionsState(
    viewModel: SelectConditionsViewModel = hiltViewModel(),
): SelectConditionsState = remember {
    SelectConditionsState(
        viewModel,
    )
}