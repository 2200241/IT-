package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeRepository {
    suspend fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<RecipeIngredient>>>
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>)
    suspend fun deleteRecipe(id: Int)
}

@Singleton
class RecipeRepositoryImpl @Inject constructor(application: Application): RecipeRepository {

    private val recipeDetailDao = RecipeAppDatabase.getDatabase(application).recipeDetailDao()

    override suspend fun getRecipeDetail(id: Int): Flow<Map<Recipe, List<RecipeIngredient>>> = recipeDetailDao.getRecipeDetail(id)

    //追加
    override suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>) {
        withContext(Dispatchers.IO){
            recipeDetailDao.addRecipe(recipe, ingredients, instructions)
        }
    }

    //指定したIDを削除
    override suspend fun deleteRecipe(id: Int){
        withContext(Dispatchers.IO) {
            recipeDetailDao.deleteRecipe(id)
        }
    }

//    //更新
//    suspend fun updateFavorite(recipe: Recipe){
//        withContext(Dispatchers.IO) {
//            recipeDetailDao.updateRecipe(recipe)
//        }
//    }
//
//    //全件削除
//    suspend fun deleteAllRecipes(){
//        withContext(Dispatchers.IO) {
//            recipeDetailDao.deleteAllRecipes()
//        }
//    }
}

