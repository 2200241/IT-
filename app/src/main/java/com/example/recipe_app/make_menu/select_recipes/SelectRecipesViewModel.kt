
package com.example.recipe_app.make_menu.select_recipes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.RecipeWithCategoryId
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.use_cases.TestUseCase
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectRecipesUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val recipeWithCategoryIds: List<RecipeWithCategoryId> = emptyList(),
    val selectedTab: CategoryTab = CategoryTab.SelectStapleFoodTab
)

@HiltViewModel
class SelectRecipesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: TestUseCase
): ViewModel() {

    private val conditions = savedStateHandle.get<String>("conditions")

    private val _uiState = MutableStateFlow(SelectRecipesUiState(
        isLoading = false,
        message = "",
        recipeWithCategoryIds = emptyList(),
        selectedTab = CategoryTab.SelectStapleFoodTab
    ))
    val uiState = _uiState.asStateFlow()

    val favoriteRecipes = useCase.fetchFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(Favorites())
    )

    val selectedRecipes = useCase.getTempMenu().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(emptyList())
    )

    init {
        viewModelScope.launch {
            startLoading()
            useCase.fetchRecipes(conditions ?: "").mapBoth(
                success = { recipes -> _uiState.update { it.copy(recipeWithCategoryIds = recipes) }},
                failure = { err -> _uiState.update { it.copy(message = err) } }
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

    fun resetMessage() {
        _uiState.update { it.copy(message = "") }
    }

    fun selectRecipe(recipe: RecipeThumb) {
        viewModelScope.launch {
            useCase.addToTempMenu(recipe)
        }
    }

    fun removeRecipe(id: Int) {
        viewModelScope.launch {
            useCase.removeFromTempMenu(id)
        }
    }

    fun addMenu() {
        viewModelScope.launch {
            useCase.addMenu().mapBoth(
                success = { message ->
                    useCase.removeAllFromTempMenu()
                    _uiState.update { it.copy(message = message) }
                          },
                failure = { err -> _uiState.update { it.copy(message = err) }}
            )
        }
    }

    fun addFavorite(recipe: RecipeWithCategoryId) {
        viewModelScope.launch {
            useCase.addFavoriteRecipe(recipe)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            useCase.removeFavoriteRecipe(id)
        }
    }

    fun onTabClicked(selectedTab: CategoryTab) {
        _uiState.update { it.copy(selectedTab = selectedTab) }
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