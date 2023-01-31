package com.example.recipe_app.settings

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.michaelbull.result.mapBoth

class SettingsScreenState(
    private val viewModel: SettingsViewModel,
    val scaffoldState: ScaffoldState?
) {
    val uiState: SettingsUiState
        @Composable get() = viewModel.uiState.collectAsState().value

    val allergens
        @Composable get() = viewModel.allergens.collectAsState().value

    fun checkAllergen(id: Int, isChecked: Boolean) = viewModel.checkAllergen(id, isChecked)

    fun resetMessage() = viewModel.resetMessage()
}

@Composable
fun rememberSettingsState(
    viewModel: SettingsViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState? = null
): SettingsScreenState = remember {
    SettingsScreenState(viewModel, scaffoldState)
}