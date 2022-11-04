package com.example.recipe_app.make_menu.select_conditions

import androidx.lifecycle.ViewModel
import com.example.recipe_app.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SelectConditionsUiState(
    val isLoading: Boolean = false,
    val selectedTab: ConditionTab = ConditionTab.SelectTagsTab
)

private val TagList = listOf(
    R.string.japanese,
    R.string.western
)

class SelectConditionsViewModel(
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectConditionsUiState(
        isLoading = false,
        selectedTab = ConditionTab.SelectTagsTab
    ))
    val uiState = _uiState.asStateFlow()

    val selectedTags = emptyList<String>()

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onTabClicked(selectedTab: ConditionTab) {
        _uiState.update { it.copy(selectedTab = selectedTab) }
    }
}

sealed class ConditionTab(
    val titleId: Int,
    val index: Int
) {
    object SelectTagsTab: ConditionTab(R.string.select_tags, 0)
    object SelectIngredientsTab: ConditionTab(R.string.select_ingredients, 1)
}

/*
internal sealed class TabPage(val route: String) {
    object SelectTags : TabPage("selectTags")
    object SelectIngredients : TabPage("selectIngredients")
}*/
