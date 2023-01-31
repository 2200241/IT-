package com.example.recipe_app.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe_app.use_cases.TestUseCase
import com.example.recipe_app.use_cases.allergy.AllergyUseCases
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val isLoading: Boolean = false,
//    val allergens: List<Allergy> = emptyList(),
    val message: String = ""
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val allergyUseCase: AllergyUseCases,
//    private val useCase: TestUseCase
//    private val recipeId: String?,
//    private val useCase: GetRecipeDetailUseCase = GetRecipeDetailUseCase()
): ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState(
        isLoading = false,
    ))
    val uiState = _uiState.asStateFlow()

    val allergens = allergyUseCase.getAllergies().stateIn(
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

    fun checkAllergen(id: Int, isChecked: Boolean) {
        viewModelScope.launch {
            allergyUseCase.checkAllergy(id, isChecked)
        }
    }

    fun setMessage(message: String) {
        _uiState.update { it.copy(message = message) }
    }

    fun resetMessage() {
        _uiState.update { it.copy(message = "") }
    }

}