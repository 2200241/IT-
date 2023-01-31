package com.example.recipe_app.menu_list.select_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.use_cases.FavoriteMenuUseCase
import com.example.recipe_app.use_cases.MenuUseCase
import com.github.michaelbull.result.Ok
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SelectMenuUiState(
    val isLoading: Boolean = false,
)

@HiltViewModel
class SelectMenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase,
    private val favoriteMenuUseCase: FavoriteMenuUseCase,
//    private val useCase: TestUseCase
//    private val menuId: String?
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectMenuUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val menus = menuUseCase.getAllMenus().stateIn(
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

    fun removeMenu(id: Int) {
        viewModelScope.launch {
            menuUseCase.deleteMenu(id)
        }
    }

    fun addFavoriteMenu(menuId: Int) {
        viewModelScope.launch {
            favoriteMenuUseCase.addFavoriteMenu(menuId)
        }
    }

    fun removeFavoriteMenu(menuId: Int) {
        viewModelScope.launch {
            favoriteMenuUseCase.deleteFavoriteMenu(menuId)
        }
    }
}