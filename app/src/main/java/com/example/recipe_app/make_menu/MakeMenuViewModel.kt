package com.example.recipe_app.make_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
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
    val selectedRecipes: Map<Recipe, List<Ingredient>> = emptyMap()
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

    fun selectRecipe(recipe: Recipe, ingredients: List<Ingredient>) {
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.plus(recipe to ingredients)) }
    }

    fun removeRecipe(id: Int) {
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.filter { entry -> entry.key.id != id }) }
    }

    fun addMenu() {
        viewModelScope.launch {
            val tempList = emptyList<RecipeIngredient>()
            _uiState.value.selectedRecipes.forEach { it.value.forEach { ingredient ->
                tempList.plus(RecipeIngredient(0, it.key.id, ingredient.name, ingredient.quantity))
            } }

            menuUseCase.addMenu(
                Menu(0, _uiState.value.selectedRecipes.map { it.key.id }),
                _uiState.value.selectedRecipes.map { it.key },
                tempList
            )
        }
    }

}