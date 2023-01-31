package com.example.recipe_app.use_cases.favorite_recipe

import com.example.recipe_app.data.RecipeWithCategoryId
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
import javax.inject.Inject

class FavoriteRecipeUseCases @Inject constructor(
    private val favoriteRecipeRepository: FavoriteRecipeRepository
) {
    fun getFavoriteRecipes() = favoriteRecipeRepository.getAllFavoriteRecipes()

    suspend fun addFavoriteRecipe(recipe: RecipeWithCategoryId) {
        favoriteRecipeRepository.addFavoriteRecipe(
            FavoriteRecipe(
                recipeId = recipe.id,
                categoryId = recipe.categoryId,
                title = recipe.title,
                thumb = recipe.thumb
            )
        )
    }

    suspend fun deleteFavoriteRecipe(id: Int) = favoriteRecipeRepository.deleteFavoriteRecipe(id)
}

