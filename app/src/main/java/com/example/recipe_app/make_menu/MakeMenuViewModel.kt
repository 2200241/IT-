package com.example.recipe_app.make_menu

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.recipe_app.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MakeMenuViewModel(
): ViewModel() {
    data class UiState(
        val isLoading: Boolean = false,
        val selectedTab: ConditionTab = ConditionTab.SelectTagsTab
    )

    private val _uiState = MutableStateFlow(UiState(
        isLoading = false,
        selectedTab = ConditionTab.SelectTagsTab
    ))
    val uiState = _uiState.asStateFlow()

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onTabClicked(selectedTab: ConditionTab) {
        _uiState.update { it.copy(selectedTab = selectedTab) }
        Log.d("debug", uiState.value.selectedTab.toString())
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
