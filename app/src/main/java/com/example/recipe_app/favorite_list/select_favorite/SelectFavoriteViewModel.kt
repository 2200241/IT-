package com.example.recipe_app.favorite_list.select_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.use_cases.FavoriteMenuUseCase
import com.example.recipe_app.use_cases.FavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectFavoriteUiState(
    val isLoading: Boolean = false,
    val selectedMainTab: FavoriteTab = FavoriteTab.SelectRecipeTab,
    val selectedSubTab: FavoriteCategoryTab = FavoriteCategoryTab.SelectStapleFoodTab
)

@HiltViewModel
class SelectFavoriteViewModel @Inject constructor(
    private val favoriteRecipeUseCase: FavoriteRecipeUseCase,
    private val favoriteMenuUseCase: FavoriteMenuUseCase,
//    private val useCase: TestUseCase,
//    private val shoppingListUseCases: ShoppingListUseCases
): ViewModel() {

    private val _uiState = MutableStateFlow(
        SelectFavoriteUiState(
            isLoading = false,
            selectedMainTab = FavoriteTab.SelectRecipeTab,
            selectedSubTab = FavoriteCategoryTab.SelectStapleFoodTab
        )
    )
    val uiState = _uiState.asStateFlow()

    val favoriteRecipes = favoriteRecipeUseCase.getFavoriteRecipes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val favoriteMenus = favoriteMenuUseCase.getFavoriteMenus().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

//    val favorites = useCase.fetchFavorites().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = Ok(Favorites())
//    )

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun addFavoriteRecipe(id: Int) {
        viewModelScope.launch {
            favoriteRecipeUseCase.addFavoriteRecipe(id)
        }
    }

    fun addFavoriteMenu(id: Int) {
        viewModelScope.launch {
            favoriteMenuUseCase.addFavoriteMenu(id)
        }
    }

    fun removeFavoriteRecipe(id: Int) {
        viewModelScope.launch {
            favoriteRecipeUseCase.deleteFavoriteRecipe(id)
        }
    }

    fun removeFavoriteMenu(id: Int) {
        viewModelScope.launch {
            favoriteMenuUseCase.deleteFavoriteMenu(id)
        }
    }

    fun onTabClicked(selectedTab: FavoriteTab) {
        _uiState.update { it.copy(selectedMainTab = selectedTab) }
    }

    fun onTabClicked(selectedTab: FavoriteCategoryTab) {
        _uiState.update { it.copy(selectedSubTab = selectedTab) }
    }

}

sealed class FavoriteTab(
    val titleId: Int,
    val index: Int
) {
    object SelectRecipeTab: FavoriteTab(R.string.recipe, 0)

    object SelectMenuTab: FavoriteTab(R.string.menu, 1)
}

sealed class FavoriteCategoryTab(
    val titleId: Int,
    val index: Int
) {
    object SelectStapleFoodTab: FavoriteCategoryTab(R.string.staple_food, 0)
    object SelectMainDishTab: FavoriteCategoryTab(R.string.main_dish, 1)
    object SelectSideDishTab: FavoriteCategoryTab(R.string.side_dish, 2)
    object SelectSoupTab: FavoriteCategoryTab(R.string.soup, 3)
    object SelectSweetsTab: FavoriteCategoryTab(R.string.sweets, 4)
    object SelectDrinkTab: FavoriteCategoryTab(R.string.drink, 5)
    object SelectOthersTab: FavoriteCategoryTab(R.string.others, 6)
}