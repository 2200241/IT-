package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class SelectConditionsState(
    private val viewModel: SelectConditionsViewModel,
) {

    val uiState: SelectConditionsUiState
        @Composable get() = viewModel.uiState.collectAsState().value

/*
    val selectedTags: List<Int>
        @Composable get() = viewModel.selectedTags.collectAsState().value
*/

    fun onTabClicked(selectedTab: ConditionTab) = viewModel.onTabClicked(selectedTab)

    fun onTagClicked(id: Int) = viewModel.onTagClicked(id)
}

@Composable
fun rememberSelectConditionsState(
    viewModel: SelectConditionsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
): SelectConditionsState = remember {
    SelectConditionsState(
        viewModel,
    )
}