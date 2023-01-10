package com.example.recipe_app.use_cases.favorite_recipe

import javax.inject.Inject

data class FavoriteRecipeUseCases @Inject constructor(
    val addFavorite: AddFavoriteRecipeUseCase,
    val getFavorite: GetFavoriteRecipesUseCase,
    val deleteFavorite: DeleteFavoriteRecipeUseCase
)

