package com.example.recipe_app.recipe_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeDetail
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.use_cases.GetRecipeDetailUseCase
import com.example.recipe_app.use_cases.TestUseCase
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipeDetail: RecipeDetail = RecipeDetail()
)

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: TestUseCase
//    private val recipeId: String?,
//    private val useCase: GetRecipeDetailUseCase = GetRecipeDetailUseCase()
): ViewModel() {

    private val recipeId = savedStateHandle.get<String>("recipeId") ?: ""
    private val thumb = savedStateHandle.get<String>("thumb") ?: ""

    private val _uiState = MutableStateFlow(RecipeDetailUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val favoriteRecipeIds = useCase.fetchFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(Favorites())
    )
    private val _favoriteRecipeIds = favoriteRecipeIds.value.mapBoth(
        success = { favorites -> favorites.recipes.map { recipe -> recipe.id } },
        failure = { emptyList() }
    )

    init {
        viewModelScope.launch {
            useCase.fetchRecipeDetail(recipeId ?: "").mapBoth(
                success = { recipe -> _uiState.update { it.copy(recipeDetail = recipe) } },
                failure = {  }
            )
        }
    }

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun onLikeClicked() {
        if (_favoriteRecipeIds.contains(recipeId)) {
            removeFavoriteRecipe()
        } else {
            addFavoriteRecipe()
        }
    }

    fun addFavoriteRecipe() {
        viewModelScope.launch {
            useCase.addFavoriteRecipe(
                Recipe(
                    id = _uiState.value.recipeDetail.id,
                    categoryId = _uiState.value.recipeDetail.categoryId,
                    title = _uiState.value.recipeDetail.title,
                    thumb = thumb
                )
            )
        }
    }

    fun removeFavoriteRecipe() {
        viewModelScope.launch {
            useCase.removeFavoriteRecipe(_uiState.value.recipeDetail.id)
        }
    }

    fun addToTempMenu() {
        viewModelScope.launch {
            useCase.addToTempMenu(RecipeThumb(id = recipeId, thumb = thumb))
        }
    }

/*
    class Factory(
        private val recipeId: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecipeDetailViewModel(recipeId = recipeId) as T
        }
    }
*/

}