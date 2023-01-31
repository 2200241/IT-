package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.FavoriteRecipeId
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeIngredient
import com.example.recipe_app.data.RecipeWithCategory
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteRecipeRepository {
    fun getAllFavoriteRecipes(): Flow<List<RecipeWithCategory>>
    fun getFavoriteRecipeIds(): Flow<List<Int>>
    suspend fun addFavoriteRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>)
    suspend fun deleteFavoriteRecipe(recipeId: Int)
}

class FavoriteRecipeRepositoryImpl @Inject constructor(application: Application): FavoriteRecipeRepository {

    private val favoriteRecipeDao = RecipeAppDatabase.getDatabase(application).favoriteRecipeDao()

    //全件取得
    override fun getAllFavoriteRecipes() = favoriteRecipeDao.getAllFavoriteRecipes()

    override fun getFavoriteRecipeIds() = favoriteRecipeDao.getFavoriteRecipeIds()

    //追加
    override suspend fun addFavoriteRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>) {
        withContext(Dispatchers.IO){
            favoriteRecipeDao.addFavoriteRecipe(FavoriteRecipeId(0, recipe.id), recipe, ingredients)
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

