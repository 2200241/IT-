package com.example.recipe_app.use_cases.favorite_recipe

import javax.inject.Inject

data class FavoriteRecipeUseCases @Inject constructor(
    val addFavoriteRecipe: AddFavoriteRecipeUseCase,
    val getFavoriteRecipes: GetFavoriteRecipesUseCase,
    val deleteFavoriteRecipe: DeleteFavoriteRecipeUseCase
)

