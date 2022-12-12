package com.example.recipe_app.menu_list.select_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.MenuWithoutIngredients
import com.example.recipe_app.use_cases.TestUseCase
import com.github.michaelbull.result.Ok
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
// 上書き防止
data class SelectMenuUiState(
    val isLoading: Boolean = false,
)

@HiltViewModel
class SelectMenuViewModel @Inject constructor(
    private val useCase: TestUseCase
//    private val menuId: String?
): ViewModel() {

    private val _uiState = MutableStateFlow(SelectMenuUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val menus = useCase.fetchMenus().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Ok(emptyList())
    )

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

    fun removeMenu(id: Int) {
        viewModelScope.launch {
            useCase.removeMenu(id)
        }
    }

    fun addFavoriteMenu(menuWithoutIngredients: MenuWithoutIngredients) {
        viewModelScope.launch {
            useCase.addFavoriteMenu(menuWithoutIngredients)
        }
    }

    fun removeFavoriteMenu(id: Int) {
        viewModelScope.launch {
            useCase.removeFavoriteMenu(id)
        }
    }
}