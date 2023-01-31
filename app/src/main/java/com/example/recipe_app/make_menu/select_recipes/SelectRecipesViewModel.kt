
package com.example.recipe_app.make_menu.select_recipes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.ApiRepository
import com.example.recipe_app.repositories.MenuRepository
import com.example.recipe_app.use_cases.FavoriteRecipeUseCase
import com.example.recipe_app.use_cases.MenuUseCase
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectRecipesUiState(
    val isLoading: Boolean = false,
    val message: String = "",
    val recipeWithCategoryIds: List<RecipeWithCategory> = emptyList(),
    val selectedTab: CategoryTab = CategoryTab.SelectStapleFoodTab,
    val selectedRecipes: Map<Recipe, List<Ingredient>> = emptyMap()
)

@HiltViewModel
class SelectRecipesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
//    private val useCase: TestUseCase,
    private val apiRepository: ApiRepository,
    private val favoriteRecipeUseCase: FavoriteRecipeUseCase,
    private val menuUseCase: MenuUseCase
): ViewModel() {

    private val conditions = savedStateHandle.get<String>("conditions")

    private val _uiState = MutableStateFlow(SelectRecipesUiState(
        isLoading = false,
        message = "",
        recipeWithCategoryIds = emptyList(),
        selectedTab = CategoryTab.SelectStapleFoodTab
    ))
    val uiState = _uiState.asStateFlow()

    val favoriteRecipeIds = favoriteRecipeUseCase.getFavoriteRecipeIds().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

//    val selectedRecipes = useCase.getTempMenu().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = Ok(emptyList())
//    )

    init {
        viewModelScope.launch {
            startLoading()
//            useCase.fetchRecipes(conditions ?: "").mapBoth(
            apiRepository.fetchRecipes(conditions ?: "").mapBoth(
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

    fun selectRecipe(recipe: Recipe, ingredients: List<Ingredient>) {
//        viewModelScope.launch {
//            useCase.addToTempMenu(recipe)
//        }
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.plus(recipe to ingredients)) }
    }

    fun removeRecipe(id: Int) {
//        viewModelScope.launch {
//            useCase.removeFromTempMenu(id)
//        }
        _uiState.update { it.copy(selectedRecipes = it.selectedRecipes.filter { entry -> entry.key.id != id }) }
    }

    fun addMenu() {
        viewModelScope.launch {
//            useCase.addMenu().mapBoth(
//                success = { message ->
//                    useCase.removeAllFromTempMenu()
//                    _uiState.update { it.copy(message = message) }
//                          },
//                failure = { err -> _uiState.update { it.copy(message = err) }}
//            )

            val ingredients = _uiState.value.selectedRecipes.map { it.value.map {
                ingredient -> RecipeIngredient(0, it.key.id, ingredient.name, ingredient.quantity)
            } }
            val tempList = emptyList<RecipeIngredient>().toMutableList()
            for (i in ingredients) {
                tempList += i
            }

            menuUseCase.addMenu(
                Menu(0, _uiState.value.selectedRecipes.map { it.key.id }),
                _uiState.value.selectedRecipes.map { it.key },
                tempList
            )
        }
    }

    fun addFavorite(id: Int) {
        viewModelScope.launch {
            favoriteRecipeUseCase.addFavoriteRecipe(id)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            favoriteRecipeUseCase.deleteFavoriteRecipe(id)
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