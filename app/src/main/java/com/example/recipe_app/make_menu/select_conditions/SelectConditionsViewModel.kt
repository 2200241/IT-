package com.example.recipe_app.make_menu.select_conditions

import androidx.lifecycle.ViewModel
import com.example.recipe_app.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SelectConditionsUiState(
    val isLoading: Boolean = false,
    val tagList: List<Int>,
    val selectedTags: List<Int>,
    val selectedTab: ConditionTab = ConditionTab.SelectTagsTab
)

private val tagList = listOf(
    R.string.japanese,
    R.string.western
)

class SelectConditionsViewModel(
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectConditionsUiState(
        isLoading = false,
        tagList = tagList,
        selectedTags = emptyList(),
        selectedTab = ConditionTab.SelectTagsTab
    ))
    val uiState = _uiState.asStateFlow()

/*
    private val _selectedTags = MutableStateFlow(checkedTags)
    val selectedTags = _selectedTags.asStateFlow()
*/

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onTabClicked(selectedTab: ConditionTab) {
        _uiState.update { it.copy(selectedTab = selectedTab) }
    }

    fun onTagClicked(id: Int) {
        if (_uiState.value.selectedTags.contains(id)) {
            _uiState.update { it.copy(selectedTags = _uiState.value.selectedTags - id) }
        } else {
            _uiState.update { it.copy(selectedTags = _uiState.value.selectedTags + id) }
        }
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
