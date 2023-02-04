package com.example.recipe_app.make_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.*
import com.example.recipe_app.use_cases.MenuUseCase
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MakeMenuUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val selectedRecipes: List<DetailedRecipe> = emptyList()
)

@HiltViewModel
class MakeMenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MakeMenuUiState(
        isLoading = false,
        message = "",
        selectedRecipes = emptyList()
    ))
    val uiState = _uiState.asStateFlow()

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun selectRecipe(recipeBase: RecipeBase) {
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.plus(
            DetailedRecipe(
                recipe = Recipe(recipeBase.id, recipeBase.categoryId, recipeBase.title, recipeBase.image, recipeBase.servings),
                ingredients = recipeBase.ingredients.map { ing -> RecipeIngredient(0, recipeBase.id, ing.name, ing.quantity) },
                instructions = recipeBase.instructions.mapIndexed { index, ins -> Instruction(0, recipeBase.id, index + 1, ins) }
            )
        )) }
        _uiState.update { it.copy(message = "Added the recipe") }
    }

    fun removeRecipe(id: Int) {
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.filter { r -> r.recipe.id != id }) }
    }

    fun addMenu() {
        if (_uiState.value.selectedRecipes.isEmpty()) {
            _uiState.update { it.copy(message = "レシピが選択されていません") }
        } else {
            viewModelScope.launch {
                menuUseCase.addMenu(_uiState.value.selectedRecipes).mapBoth(
                    success = { message ->
                        _uiState.update { it.copy(
                                selectedRecipes = emptyList(),
                                message = message
                        )}
                    },
                    failure = { message -> _uiState.update { it.copy(message = message) } }
                )
            }
        }
    }

    fun resetMessage() {
        _uiState.update { it.copy(message = "") }
    }

}