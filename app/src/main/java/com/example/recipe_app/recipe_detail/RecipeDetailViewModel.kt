package com.example.recipe_app.recipe_detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.RecipeBase
import com.example.recipe_app.data.RecipeIngredient
import com.example.recipe_app.repositories.ApiRepository
import com.example.recipe_app.use_cases.FavoriteRecipeUseCase
import com.example.recipe_app.use_cases.RecipeUseCase
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RecipeDetailUiState(
    val isLoading: Boolean = false,
    val recipe: RecipeBase = RecipeBase(),
//    val ingredients: List<Ingredient> = emptyList(),
    val message: String = ""
)

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val recipeUseCase: RecipeUseCase,
    private val favoriteRecipeUseCase: FavoriteRecipeUseCase,
    private val apiRepository: ApiRepository
//    private val useCase: TestUseCase
//    private val recipeId: String?,
//    private val useCase: GetRecipeDetailUseCase = GetRecipeDetailUseCase()
): ViewModel() {

    private val recipeId = savedStateHandle.get<String>("recipeId")?.toInt() ?: -1
//    private val thumb = savedStateHandle.get<String>("thumb") ?: ""

    private val _uiState = MutableStateFlow(RecipeDetailUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val favoriteRecipeIds = favoriteRecipeUseCase.getFavoriteRecipeIds().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            startLoading()
            recipeUseCase.getRecipeDetail(recipeId).mapBoth(
                success = { recipe ->
                    _uiState.update { it.copy(recipe = RecipeBase(
                        id = recipe.recipe.id,
                        categoryId = recipe.recipe.categoryId,
                        title = recipe.recipe.title,
                        image = recipe.recipe.image,
                        servings = recipe.recipe.servings,
                        ingredients = recipe.ingredients.map { i -> Ingredient(i.name, i.quantity) },
                        instructions = recipe.instructions.map { i -> i.content }
                    )) }
                },
                failure = {
                    apiRepository.fetchRecipeById(recipeId).mapBoth(
                        success = { recipe -> _uiState.update { it.copy(recipe = recipe) } },
                        failure = { message -> _uiState.update { it.copy(message = message) }}
                    )
                }
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

    fun addFavoriteRecipe() {
        viewModelScope.launch {
            favoriteRecipeUseCase.addFavoriteRecipe(recipeId)
        }
    }

    fun removeFavoriteRecipe() {
        viewModelScope.launch {
            favoriteRecipeUseCase.deleteFavoriteRecipe(recipeId)
        }
    }

    // TODO: 二重登録防止
//    fun addToTempMenu() {
//        viewModelScope.launch {
//            useCase.addToTempMenu(RecipeThumb(id = recipeId, thumb = thumb)).mapBoth(
//                success = { setMessage(it) },
//                failure = { setMessage(it) }
//            )
//        }
//    }

    fun setMessage(message: String) {
        _uiState.update { it.copy(message = message) }
    }

    fun resetMessage() {
        _uiState.update { it.copy(message = "") }
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