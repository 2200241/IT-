package com.example.recipe_app.make_menu.select_conditions

import androidx.lifecycle.ViewModel
import com.example.recipe_app.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class SelectConditionsUiState(
    val isLoading: Boolean = false,
    val largeCategories: List<LargeCategory>,
    val selectedTags: List<Int>,
    val keywords: String,
    val selectedTab: ConditionTab = ConditionTab.SelectTagsTab
)

//呼び出すより先に記述しないと呼び出されなかった？
private val categories = listOf(
    R.string.staple_food,
    R.string.main_dish,
    R.string.side_dish,
    R.string.soup,
    R.string.sweets,
    R.string.drink,
    R.string.others
)

private val styles= listOf(
    R.string.japanese,
    R.string.western
)

private val largeCategories = listOf(
    LargeCategory.Categories,
    LargeCategory.Styles
)

sealed class LargeCategory(
    val id: Int,
    val items: List<Int>
) {
    object Categories: LargeCategory(R.string.category, categories)
    object Styles: LargeCategory(R.string.style, styles)
}

@HiltViewModel
class SelectConditionsViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectConditionsUiState(
        isLoading = false,
        largeCategories = largeCategories,
        selectedTags = emptyList(),
        keywords = "",
        selectedTab = ConditionTab.SelectTagsTab
    ))
    val uiState = _uiState.asStateFlow()

/*
    private val _selectedTags = MutableStateFlow(checkedTags)
    val selectedTags = _selectedTags.asStateFlow()
*/

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
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

    fun onClearClicked() {
        _uiState.update { it.copy(selectedTags = emptyList()) }
    }

    fun setKeywords(text: String) {
        _uiState.update { it.copy(keywords = text) }
    }

    fun getSuggestions(keywords: String) {

    }

    fun getConditions(): String {
        // TODO: Add ingredients
        val separator = "&"
        val conditions = _uiState.value.selectedTags.joinToString(separator)
        // TODO: Catch exceptions
        return conditions
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
