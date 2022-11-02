package com.example.recipe_app.recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
)

class RecipeDetailViewModel(
    private val recipeId: String?
): ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetailUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun getRecipeData() {

    }

    companion object {
        fun <T: ViewModel?>provideFactory(recipeId: String?): T {
            return RecipeDetailViewModel(recipeId = recipeId) as T
        }
    }

}