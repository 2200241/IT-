package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteRecipeRepository {
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithCategory>>
    fun getFavoriteRecipeIds(): Flow<List<Int>>
    suspend fun addFavoriteRecipe(detailedRecipe: DetailedRecipe): Result<String, String>
    suspend fun deleteFavoriteRecipe(recipeId: Int): Result<String, String>
}

class FavoriteRecipeRepositoryImpl @Inject constructor(application: Application): FavoriteRecipeRepository {
    private val favoriteRecipeDao = RecipeAppDatabase.getDatabase(application).favoriteRecipeDao()

    //全件取得
    override fun getAllFavoriteRecipes() = favoriteRecipeDao.getAllFavoriteRecipes()

    override fun getFavoriteRecipeIds() = favoriteRecipeDao.getFavoriteRecipeIds()

    //追加
    override suspend fun addFavoriteRecipe(detailedRecipe: DetailedRecipe): Result<String, String> {
        try {
            val ingredients = mutableListOf<RecipeIngredient>()
            val instruction = mutableListOf<Instruction>()

            detailedRecipe.ingredients.forEach { ingredients.add(it) }
            detailedRecipe.instructions.forEach { instruction.add(it) }

            return withContext(Dispatchers.IO) {
                favoriteRecipeDao.addFavoriteRecipe(
                    detailedRecipe.recipe,
                    FavoriteRecipeId(0, detailedRecipe.recipe.id),
                    ingredients,
                    instruction
                )
                return@withContext Ok("Success")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    //指定したIDを削除
    override suspend fun deleteFavoriteRecipe(recipeId: Int): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                favoriteRecipeDao.deleteFavoriteRecipe(recipeId)
                return@withContext Ok("Success")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

//    //更新
//    suspend fun updateFavorite(favoriteRecipeId: FavoriteRecipeId) {
//        withContext(Dispatchers.IO) {
//            favoriteRecipeDao.updateFavoriteRecipe(favoriteRecipeId)
//        }
//    }
//
//    //全件削除
//    suspend fun deleteAllFavoriteRecipes() {
//        withContext(Dispatchers.IO) {
//            favoriteRecipeDao.deleteAllFavoriteRecipes()
//        }
//    }
}

