package com.example.recipe_app.use_cases

import javax.inject.Inject

data class FavoriteUseCases @Inject constructor(
    val addFavorite: AddFavoriteUseCase,
    val getFavorite: GetFavoritesUseCase,
    val deleteFavorite: DeleteFavoriteUseCase
)

