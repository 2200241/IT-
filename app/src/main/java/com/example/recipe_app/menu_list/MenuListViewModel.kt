package com.example.recipe_app.menu_list

import androidx.lifecycle.ViewModel
import com.example.recipe_app.use_cases.TestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class MenuListUiState(
    val isLoading: Boolean = false,
)

@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val useCase: TestUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(MenuListUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()


    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }
}