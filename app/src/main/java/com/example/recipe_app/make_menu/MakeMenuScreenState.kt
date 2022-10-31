package com.example.recipe_app.make_menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember

class MakeMenuScreenState(
    private val viewModel: MakeMenuViewModel,
) {

    val uiState: MakeMenuViewModel.UiState
        @Composable get() = viewModel.uiState.collectAsState().value
}

@Composable
fun rememberMakeMenuScreenState(
    viewModel: MakeMenuViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
): MakeMenuScreenState = remember {
    MakeMenuScreenState(
        viewModel,
    )
}