package com.example.recipe_app.menu_list.select_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.data.Menu
import com.example.recipe_app.use_cases.TestUseCase
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

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

    fun removeMenu(id: String) {
        viewModelScope.launch {
            useCase.removeMenu(id)
        }
    }

    fun addFavoriteMenu(menu: Menu) {
        viewModelScope.launch {
            useCase.addFavoriteMenu(menu)
        }
    }

    fun removeFavoriteMenu(id: String) {
        viewModelScope.launch {
            useCase.removeFavoriteMenu(id)
        }
    }
}