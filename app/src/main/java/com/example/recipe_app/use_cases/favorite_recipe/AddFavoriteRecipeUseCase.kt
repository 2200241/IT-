package com.example.recipe_app.use_cases.favorite_recipe

import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
import javax.inject.Inject

class AddFavoriteRecipeUseCase @Inject constructor(
    private val repository: FavoriteRecipeRepository
) {

    @Throws(InvalidAddFavoriteException::class)
    suspend fun addFavorite(favoriteRecipe: FavoriteRecipe) {
        //データ削除 これは最後に消す
//        repository.deleteAllFavoriteRecipes()

        //エラー処理
        if(favoriteRecipe.id.toString().isBlank()) {
            throw InvalidAddFavoriteException("The recipe_id of the note can't be empty.")
        }
        if(favoriteRecipe.title.isBlank()) {
            throw InvalidAddFavoriteException("The name of the note can't be empty.")
        }

        repository.addFavoriteRecipe(favoriteRecipe)
    }
}


class InvalidAddFavoriteException(message: String): Exception(message)
