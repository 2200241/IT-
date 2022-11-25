package com.example.recipe_app.make_menu.select_recipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.recipe_app.make_menu.select_conditions.ConditionTab

class SelectRecipesState(
    private val viewModel: SelectRecipesViewModel,
) {
    val uiState: SelectRecipesUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    fun onTabClicked(selectedTab: CategoryTab) = viewModel.onTabClicked(selectedTab)

}

@Composable
fun rememberSelectRecipesState(
    conditions: String?,
    viewModel: SelectRecipesViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = SelectRecipesViewModel.Factory(conditions)
    )
): SelectRecipesState = remember {
    SelectRecipesState(viewModel)
}