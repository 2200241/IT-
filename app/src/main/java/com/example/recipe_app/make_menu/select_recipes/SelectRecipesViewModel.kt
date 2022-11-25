
package com.example.recipe_app.make_menu.select_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.make_menu.select_conditions.ConditionTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SelectRecipesUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val testString: String = "",
    val selectedTab: CategoryTab = CategoryTab.SelectStapleFoodTab
)

class SelectRecipesViewModel(
    private val conditions: String?
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectRecipesUiState(
        isLoading = false,
        recipes = emptyList(),
        selectedTab = CategoryTab.SelectStapleFoodTab
    ))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
/*
            useCase.fetch(conditions)
                .onSuccess { recipes ->
                    _uiState.update { it.copy(
                        recipes = recipes
                    ) }
                }
*/
//            _uiState.update { it.copy(recipes = emptyList()) }
            _uiState.update { it.copy(testString = conditions ?: "") }
        }
    }

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onTabClicked(selectedTab: CategoryTab) {
        _uiState.update { it.copy(selectedTab = selectedTab) }
    }

    class Factory(
        private val conditions: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectRecipesViewModel(conditions = conditions) as T
        }
    }
}

sealed class CategoryTab(
    val titleId: Int,
    val index: Int
) {
    object SelectStapleFoodTab: CategoryTab(R.string.staple_food, 0)
    object SelectMainDishTab: CategoryTab(R.string.main_dish, 1)

    object SelectSideDishTab: CategoryTab(R.string.side_dish, 2)

    object SelectSoupTab: CategoryTab(R.string.soup, 3)

    object SelectSweetsTab: CategoryTab(R.string.sweets, 4)

    object SelectDrinkTab: CategoryTab(R.string.drink, 5)

    object SelectOthersTab: CategoryTab(R.string.others, 6)
}