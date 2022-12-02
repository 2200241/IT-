package com.example.recipe_app.favorite_list.select_favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.R
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.use_cases.TestUseCase
import com.github.michaelbull.result.Ok
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectFavoriteUiState(
    val isLoading: Boolean = false,
    val selectedTab: FavoriteTab = FavoriteTab.SelectRecipeTab
)

@HiltViewModel
class SelectFavoriteViewModel @Inject constructor(
    private val useCase: TestUseCase
//    useCase: GetFavoritesUseCase = GetFavoritesUseCase()
): ViewModel() {

    private val _uiState = MutableStateFlow(
        SelectFavoriteUiState(
            isLoading = false,
            selectedTab = FavoriteTab.SelectRecipeTab
        )
    )
    val uiState = _uiState.asStateFlow()

    val favorites = useCase.fetchFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(Favorites())
    )

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun addFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            useCase.addFavoriteRecipe(recipe)
        }
    }

    fun addFavoriteMenu(menu: Menu) {
        viewModelScope.launch {
            useCase.addFavoriteMenu(menu)
        }
    }

    fun removeFavoriteRecipe(id: String) {
        viewModelScope.launch {
            useCase.removeFavoriteRecipe(id)
        }
    }

    fun removeFavoriteMenu(id: String) {
        viewModelScope.launch {
            useCase.removeFavoriteMenu(id)
        }
    }

    fun onTabClicked(selectedTab: FavoriteTab) {
        _uiState.update { it.copy(selectedTab = selectedTab) }
    }

}

sealed class FavoriteTab(
    val titleId: Int,
    val index: Int
) {
    object SelectRecipeTab: FavoriteTab(R.string.recipe, 0)

    object SelectMenuTab: FavoriteTab(R.string.menu, 1)
}