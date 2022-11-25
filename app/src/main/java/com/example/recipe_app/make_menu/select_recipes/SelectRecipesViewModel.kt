
package com.example.recipe_app.make_menu.select_recipes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.use_cases.TestUseCase
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectRecipesUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val recipes: List<Recipe> = emptyList(),
    val selectedRecipes: List<RecipeThumb> = emptyList()
)

@HiltViewModel
class SelectRecipesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: TestUseCase
//    private val conditions: String?
): ViewModel() {

    private val conditions = savedStateHandle.get<String>("conditions")

    private val _uiState = MutableStateFlow(SelectRecipesUiState(
        isLoading = false,
        error = "",
        recipes = emptyList(),
        selectedRecipes = emptyList()
    ))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            startLoading()
            useCase.fetchRecipes(conditions ?: "").mapBoth(
                success = { recipes -> _uiState.update { it.copy(recipes = recipes) }},
                failure = { error -> _uiState.update { it.copy(error = error) } }
            )
            endLoading()
        }
    }

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun selectRecipe(id: String, thumb: String) {
        _uiState.update { it.copy(selectedRecipes = _uiState.value.selectedRecipes + listOf(RecipeThumb(id, thumb))) }
    }

    fun removeRecipe() {

    }

    fun addMenu() {

    }

/*
    class Factory(
        private val conditions: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SelectRecipesViewModel(conditions = conditions) as T
        }
    }
*/
}