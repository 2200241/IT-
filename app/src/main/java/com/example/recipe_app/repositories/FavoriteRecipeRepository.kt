package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRecipeRepository @Inject constructor(application: Application) {

    private val favoriteRecipeDao = RecipeAppDatabase.getDatabase(application).favoriteRecipeDao()

    //全件取得
    fun getAllFavoriteRecipes() = favoriteRecipeDao.getAllFavoriteRecipes()

    //追加
    suspend fun addFavoriteRecipe(favoriteRecipe: FavoriteRecipe) {
        withContext(Dispatchers.IO){
            favoriteRecipeDao.addFavoriteRecipe(favoriteRecipe)
        }
    }

    //指定したIDを削除
    suspend fun deleteFavoriteRecipe(id: Int){
        withContext(Dispatchers.IO) {
            favoriteRecipeDao.deleteFavoriteRecipe(id)
        }
    }

    //更新
    suspend fun updateFavorite(favoriteRecipe: FavoriteRecipe){
        withContext(Dispatchers.IO) {
            favoriteRecipeDao.updateFavoriteRecipe(favoriteRecipe)
        }
    }

    //全件削除
    suspend fun deleteAllFavoriteRecipes(){
        withContext(Dispatchers.IO) {
            favoriteRecipeDao.deleteAllFavoriteRecipes()
        }
    }
}

