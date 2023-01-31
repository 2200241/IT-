package com.example.recipe_app.favorite_list.select_favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.MenuWithoutIngredients
import com.example.recipe_app.data.RecipeWithCategoryId
import com.example.recipe_app.repositories.ApiRepository
import com.example.recipe_app.repositories.ShoppingListRepository
import com.example.recipe_app.room.allergy.Allergy
import com.example.recipe_app.use_cases.shoppinglist.AddShoppingListUseCase
import com.example.recipe_app.use_cases.shoppinglist.GetShoppingListsUseCase
import com.example.recipe_app.use_cases.TestUseCase
import com.example.recipe_app.use_cases.allergy.AllergyUseCases
import com.example.recipe_app.use_cases.favorite_menu.FavoriteMenuUseCases
import com.example.recipe_app.use_cases.favorite_recipe.FavoriteRecipeUseCases
import com.example.recipe_app.use_cases.shoppinglist.AddShoppingItemUseCase
import com.example.recipe_app.use_cases.shoppinglist.ShoppingListUseCases
import com.github.michaelbull.result.Ok
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
    private val favoriteRecipeUseCases: FavoriteRecipeUseCases,
    private val favoriteMenuUseCases: FavoriteMenuUseCases,
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

    val favoriteRecipes = favoriteRecipeUseCases.getFavoriteRecipes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val favoriteMenus = favoriteMenuUseCases.getFavoriteMenus().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
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

    fun addFavoriteRecipe(recipe: RecipeWithCategoryId) {
        viewModelScope.launch {
            favoriteRecipeUseCases.addFavoriteRecipe(recipe)
        }
    }

    fun addFavoriteMenu(menu: MenuWithoutIngredients) {
        viewModelScope.launch {
            favoriteMenuUseCases.addFavoriteMenu(menu)
        }
    }

    fun removeFavoriteRecipe(id: Int) {
        viewModelScope.launch {
            favoriteRecipeUseCases.deleteFavoriteRecipe(id)
        }
    }

    fun removeFavoriteMenu(id: Int) {
        viewModelScope.launch {
            favoriteMenuUseCases.deleteFavoriteMenu(id)
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