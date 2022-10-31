package com.example.recipe_app.make_menu.select_conditions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.recipe_app.make_menu.ConditionTab
import com.example.recipe_app.make_menu.MakeMenuViewModel

class MakeMenuScreenState(
    private val viewModel: MakeMenuViewModel,
) {

    val uiState: MakeMenuViewModel.UiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun onTabClicked(selectedTab: ConditionTab) = viewModel.onTabClicked(selectedTab)
}

@Composable
fun rememberMakeMenuScreenState(
    viewModel: MakeMenuViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
): MakeMenuScreenState = remember {
    MakeMenuScreenState(
        viewModel,
    )
}