package com.example.recipe_app.favorite_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class FavoriteListUiState(
    val isLoading: Boolean = false,
    val error: String = ""
)

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteListUiState(
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