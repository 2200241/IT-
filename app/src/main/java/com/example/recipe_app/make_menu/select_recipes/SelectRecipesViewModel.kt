
package com.example.recipe_app.make_menu.select_recipes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
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
    val error: String = "",
    val recipes: List<Recipe> = emptyList(),
    val selectedRecipes: List<RecipeThumb> = emptyList(),
    val selectedTab: CategoryTab = CategoryTab.SelectStapleFoodTab
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
        selectedRecipes = emptyList(),
        selectedTab = CategoryTab.SelectStapleFoodTab
    ))
    val uiState = _uiState.asStateFlow()

    val favoriteRecipeIds = useCase.fetchFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(Favorites())
    )

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

    fun selectRecipe(recipe: RecipeThumb) {
        _uiState.update { it.copy(selectedRecipes = _uiState.value.selectedRecipes + listOf(recipe)) }
    }

    fun removeRecipe(id: String) {
        _uiState.update { it.copy(
            selectedRecipes = _uiState.value.selectedRecipes.filter { recipe -> recipe.id != id }
        ) }
    }

    fun addMenu() {
        viewModelScope.launch {
            useCase.addMenu(Menu(id = "", date = "", recipes = _uiState.value.selectedRecipes))
        }
    }

    fun addFavorite(recipe: Recipe) {
        viewModelScope.launch {
            useCase.addFavoriteRecipe(recipe)
        }
    }

    fun removeFavorite(id: String) {
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