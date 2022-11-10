
package com.example.recipe_app.make_menu.select_recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.recipe_detail.RecipeDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SelectRecipesUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val testString: String = ""
)

data class Recipe(
    val title: String,
    val thumb: String,
    val isFavorite: Boolean
)

class SelectRecipesViewModel(
    private val conditions: String?
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectRecipesUiState(
        isLoading = false,
        recipes = emptyList()
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

    class Factory(
        private val conditions: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectRecipesViewModel(conditions = conditions) as T
        }
    }
}