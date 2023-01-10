package com.example.recipe_app.use_cases.favorite_recipe

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteRecipesUseCase @Inject constructor(private val favoriteRecipeRepository: FavoriteRecipeRepository){

    var favoriteRecipeList: Flow<List<FavoriteRecipe>> = favoriteRecipeRepository.getAllFavoriteRecipes()

    @Throws(InvalidGetFavoritesException::class)
    suspend fun getFavoriteRecipe(): Flow<List<FavoriteRecipe>> {

        favoriteRecipeList.collect {
            if (it.isEmpty()) {
                throw InvalidGetFavoritesException("お気に入りがありません")
            }
        }

        return favoriteRecipeList
    }

    // サンプルデータ
    suspend fun setTestFavoriteData(): Flow<List<FavoriteRecipe>> {

        favoriteRecipeRepository.deleteAllFavoriteRecipes()
        favoriteRecipeRepository.addFavoriteRecipe(FavoriteRecipe(0,0 , 0,"title", "thumb"))
        favoriteRecipeList= favoriteRecipeRepository.getAllFavoriteRecipes()

        return favoriteRecipeList
    }
}

class InvalidGetFavoritesException(message: String): Exception(message)

