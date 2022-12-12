package com.example.recipe_app.make_menu

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
// 上書き防止
data class MakeMenuUiState(
    val isLoading: Boolean = false,
    val error: String = ""
)

@HiltViewModel
class MakeMenuViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableStateFlow(MakeMenuUiState(
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