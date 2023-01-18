package com.example.recipe_app.use_cases.menu

import javax.inject.Inject

data class MenuUseCases @Inject constructor(
    val addMenu: AddMenuUseCase,
    val getMenu: GetMenusUseCase,
    val deleteMenu: DeleteMenuUseCase
)

