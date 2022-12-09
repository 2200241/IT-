package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.recipe.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(application: Application) {

    private val recipeDao = RecipeAppDatabase.getDatabase(application).recipeDao()

    //全件取得
    suspend fun getAllRecipes(): List<Recipe> = recipeDao.getAllRecipes()

    //追加
    suspend fun addRecipe(recipe: Recipe) {
        withContext(Dispatchers.IO){
            recipeDao.addRecipe(recipe)
        }
    }

    //指定したIDを削除
    suspend fun deleteRecipe(id: Int){
        withContext(Dispatchers.IO) {
            recipeDao.deleteRecipe(id)
        }
    }

    //更新
    suspend fun updateFavorite(recipe: Recipe){
        withContext(Dispatchers.IO) {
            recipeDao.updateRecipe(recipe)
        }
    }

    //全件削除
    suspend fun deleteAllRecipes(){
        withContext(Dispatchers.IO) {
            recipeDao.deleteAllRecipes()
        }
    }
}

