package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class SelectConditionsState(
    private val viewModel: SelectConditionsViewModel,
) {

    val uiState: SelectConditionsViewModel.UiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun onTabClicked(selectedTab: ConditionTab) = viewModel.onTabClicked(selectedTab)
}

@Composable
fun rememberSelectConditionsState(
    viewModel: SelectConditionsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
): SelectConditionsState = remember {
    SelectConditionsState(
        viewModel,
    )
}