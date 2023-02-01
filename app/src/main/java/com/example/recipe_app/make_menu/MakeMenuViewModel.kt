package com.example.recipe_app.make_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.*
import com.example.recipe_app.use_cases.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MakeMenuUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val selectedRecipes: List<RecipeDetail> = emptyList()
)

@HiltViewModel
class MakeMenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MakeMenuUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun selectRecipe(recipeDetail: RecipeDetail) {
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.plus(recipeDetail)) }
    }

    fun removeRecipe(id: Int) {
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.filter { r -> r.recipe.id != id }) }
    }

    fun addMenu() {
        viewModelScope.launch {
            menuUseCase.addMenu(_uiState.value.selectedRecipes)
            _uiState.update { it.copy(selectedRecipes = emptyList()) }
        }
    }

}