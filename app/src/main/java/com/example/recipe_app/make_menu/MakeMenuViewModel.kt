package com.example.recipe_app.make_menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class MakeMenuUiState(
    val isLoading: Boolean = false,
)

class MakeMenuViewModel(
): ViewModel() {

    private val _uiState = MutableStateFlow(MakeMenuUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    suspend fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    suspend fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }
}