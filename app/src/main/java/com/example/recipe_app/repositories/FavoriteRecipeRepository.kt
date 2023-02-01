package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteRecipeRepository {
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithCategory>>
    fun getFavoriteRecipeIds(): Flow<List<Int>>
    suspend fun addFavoriteRecipe(recipeDetail: RecipeDetail)
    suspend fun deleteFavoriteRecipe(recipeId: Int)
}

class FavoriteRecipeRepositoryImpl @Inject constructor(application: Application): FavoriteRecipeRepository {

    private val favoriteRecipeDao = RecipeAppDatabase.getDatabase(application).favoriteRecipeDao()

    //全件取得
    override fun getAllFavoriteRecipes() = favoriteRecipeDao.getAllFavoriteRecipes()

    override fun getFavoriteRecipeIds() = favoriteRecipeDao.getFavoriteRecipeIds()

    //追加
    override suspend fun addFavoriteRecipe(recipeDetail: RecipeDetail) {
        val ingredients = emptyList<RecipeIngredient>()
        val instruction = emptyList<Instruction>()

        recipeDetail.ingredients.forEach { ingredients.plus(it) }
        recipeDetail.instructions.forEach { instruction.plus(it) }

        withContext(Dispatchers.IO){
            favoriteRecipeDao.addFavoriteRecipe(
                FavoriteRecipeId(0, recipeDetail.recipe.id),
                recipeDetail.recipe,
                ingredients,
                instruction
            )
        }
    }

    //指定したIDを削除
    override suspend fun deleteFavoriteRecipe(recipeId: Int){
        withContext(Dispatchers.IO) {
            favoriteRecipeDao.deleteFavoriteRecipe(recipeId)
        }
    }

//    //更新
//    suspend fun updateFavorite(favoriteRecipeId: FavoriteRecipeId){
//        withContext(Dispatchers.IO) {
//            favoriteRecipeDao.updateFavoriteRecipe(favoriteRecipeId)
//        }
//    }
//
//    //全件削除
//    suspend fun deleteAllFavoriteRecipes(){
//        withContext(Dispatchers.IO) {
//            favoriteRecipeDao.deleteAllFavoriteRecipes()
//        }
//    }
}

