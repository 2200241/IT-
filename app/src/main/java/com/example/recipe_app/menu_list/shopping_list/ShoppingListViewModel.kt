package com.example.recipe_app.menu_list.shopping_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.recipe_detail.RecipeDetailViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ShoppingListUiState(
    val isLoading: Boolean = false,
    val menuId: String = "",
)

class ShoppingListViewModel(
    private val menuId: String?
): ViewModel() {

    private val _uiState = MutableStateFlow(ShoppingListUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
/*
            useCase.fetch(menuId)
                .onSuccess { shoppingList ->
                    _uiState.update { it.copy(
                        shoppingList = shoppingList
                    ) }
                }
*/
            _uiState.update { it.copy(menuId = "TEST@$menuId") }
        }
    }

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    class Factory(
        private val menuId: String?
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingListViewModel(menuId = menuId) as T
        }
    }
}