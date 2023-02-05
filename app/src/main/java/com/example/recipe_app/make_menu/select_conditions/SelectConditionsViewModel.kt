package com.example.recipe_app.make_menu.select_conditions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.repositories.ApiRepository
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectConditionsUiState(
    val isLoading: Boolean = false,
//    val largeCategories: List<LargeCategory>,
    val selectedTags: List<Int> = emptyList(),
    val selectedIngredients: List<Int> = emptyList(),
    val keywords: String = "",
    val tagKeyword: String = "",
    val selectedTab: ConditionTab = ConditionTab.SelectTagsTab,
    val ingredients: Map<Int, String> = emptyMap(),
    val tags: Map<Int, String> = emptyMap()
)

//呼び出すより先に記述しないと呼び出されなかった？
/*private val categories = listOf(
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
}*/

@HiltViewModel
class SelectConditionsViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectConditionsUiState(
        isLoading = false,
//        largeCategories = largeCategories,
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

    fun onIngredientClicked(id: Int) {
        if (_uiState.value.selectedIngredients.contains(id)) {
            _uiState.update { it.copy(selectedIngredients = _uiState.value.selectedIngredients - id) }
        } else {
            _uiState.update { it.copy(selectedIngredients = _uiState.value.selectedIngredients + id) }
        }
    }

    fun onClearClicked() = _uiState.update { it.copy(selectedTags = emptyList()) }

    fun setKeywords(text: String) = _uiState.update { it.copy(keywords = text) }

    fun setTagKeyword(text: String) = _uiState.update { it.copy(tagKeyword = text) }

    fun getIngredientSuggestions() {
        viewModelScope.launch {
            apiRepository.fetchSuggestions(_uiState.value.keywords, "ingredients").mapBoth(
                success = { res -> _uiState.update { it.copy(ingredients = res) } },
                failure = { }
            )

        }
    }

    fun getTagSuggestions() {
        viewModelScope.launch {
            apiRepository.fetchSuggestions(_uiState.value.tagKeyword, "tags").mapBoth(
                success = { res -> _uiState.update { it.copy(tags = res) } },
                failure = { }
            )
        }
    }

    fun getConditions(): String {
        var conditions = ""
        val separator = "&"
        val delimiter = ","

        if (_uiState.value.selectedIngredients.isNotEmpty()) {
            conditions += "ingredients=" + _uiState.value.selectedIngredients.joinToString(delimiter) + separator
        }
        if (_uiState.value.selectedTags.isNotEmpty()) {
            conditions += "tags=" + _uiState.value.selectedTags.joinToString(delimiter)
        }

//        return conditions
        return "title=" + _uiState.value.keywords
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
