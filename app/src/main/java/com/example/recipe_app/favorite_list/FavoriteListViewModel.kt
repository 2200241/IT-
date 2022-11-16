package com.example.recipe_app.favorite_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Categories
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FavoriteListUiState(
    val isLoading: Boolean = false,
    val favorites: Favorites = Favorites()
)

class FavoriteListViewModel(
//    useCase: GetFavoritesUseCase = GetFavoritesUseCase()
): ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteListUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
/*
            useCase.fetch()
                .onSuccess { favorites ->
                    _uiState.update { it.copy(
                        favorites = favorites
                    ) }
                }
*/
            setTestList()
        }
    }

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun deleteFavorite() {

    }

    private fun setTestList() {
        val testArray = emptyArray<Recipe>()
        for (i in 0..9) {
            testArray[i] = Recipe(
                title = "料理$i",
                thumb = "url",
                isFavorite = true
            )
        }
        val testList = testArray.asList()
        val testMenus = emptyArray<Menu>()
        for (i in 0..9) {
            testMenus[i] = Menu(id = "1", date = "2022-1-1", recipes = testList)
        }
        val testCategories = Categories(
            stapleFood = testList,
            mainDish = testList,
            sideDish = testList,
            soup = testList,
            sweets = testList,
            drink = testList,
            others = testList
        )

        val testFavorites = Favorites(
            menus = testMenus.asList(),
            categories = testCategories
        )
        _uiState.update { it.copy(favorites = testFavorites) }
    }
}