package com.example.recipe_app.menu_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MenuListUiState(
    val isLoading: Boolean = false,
)

@HiltViewModel
class MenuListViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableStateFlow(MenuListUiState(
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