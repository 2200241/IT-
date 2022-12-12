package com.example.recipe_app.menu_list.shopping_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.MenuWithRecipeThumbs
import com.example.recipe_app.use_cases.TestUseCase
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
    private val useCase: TestUseCase
//    private val menuId: String?
): ViewModel() {

    private val menuId = savedStateHandle.get<String>("menuId")?.toInt() ?: -1

    private val _uiState = MutableStateFlow(ShoppingListUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val menuWithRecipeThumbs = useCase.fetchMenu(menuId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(MenuWithRecipeThumbs())
    )

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun checkShoppingListItem(index: Int) {
        viewModelScope.launch {
            useCase.checkShoppingListItem(menuId, index)
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