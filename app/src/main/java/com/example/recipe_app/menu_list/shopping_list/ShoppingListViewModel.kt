package com.example.recipe_app.menu_list.shopping_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.use_cases.FavoriteMenuUseCase
import com.example.recipe_app.use_cases.MenuUseCase
import com.github.michaelbull.result.Ok
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ShoppingListUiState(
    val isLoading: Boolean = false,
//    val menuDetail: MenuDetail = MenuDetail(),
)

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val menuUseCase: MenuUseCase,
    private val favoriteMenuUseCase: FavoriteMenuUseCase
//    private val useCase: TestUseCase
//    private val menuId: String?
): ViewModel() {

    val menuId = savedStateHandle.get<String>("menuId")?.toInt() ?: -1

    private val _uiState = MutableStateFlow(ShoppingListUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val menuDetail = menuUseCase.getMenuDetail(menuId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    val favoriteMenuIds = favoriteMenuUseCase.getFavoriteMenuIds().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun checkShoppingListItem(id: Int) {
        viewModelScope.launch {
            menuUseCase.checkShoppingItem(id)
        }
    }

    fun addFavorite(id: Int) {
        viewModelScope.launch {
            favoriteMenuUseCase.addFavoriteMenu(id)
        }
    }

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            favoriteMenuUseCase.deleteFavoriteMenu(id)
        }
    }

/*
    class Factory(
        private val menuId: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingListViewModel(menuId = menuId) as T
        }
    }
*/
}