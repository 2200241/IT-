package com.example.recipe_app.use_cases.favorite_menu

import javax.inject.Inject

data class FavoriteMenuUseCases @Inject constructor(
    val addFavoriteMenu: AddFavoriteMenuUseCase,
    val getFavoriteMenus: GetFavoriteMenusUseCase,
    val deleteFavoriteMenu: DeleteFavoriteMenuUseCase
)

