package com.example.recipe_app.recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.use_cases.GetRecipeDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipeId: String = "",
    val title: String = ""
)

class RecipeDetailViewModel(
    private val recipeId: String?,
    private val useCase: GetRecipeDetailUseCase = GetRecipeDetailUseCase()
): ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetailUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
/*
            useCase.fetch(recipeId)
                .onSuccess { recipe ->
                    _uiState.update { it.copy(
                        title = recipe.title
                    ) }
                }
*/
            _uiState.update { it.copy(title = "TEST@$recipeId") }
        }
    }

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    companion object {
        fun <T: ViewModel?>provideFactory(recipeId: String?): T {
            return RecipeDetailViewModel(recipeId = recipeId) as T
        }
    }

    class Factory(
        private val recipeId: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipeDetailViewModel(recipeId = recipeId) as T
        }
    }

}