package com.example.recipe_app.repositories

import android.app.Application
import androidx.room.withTransaction
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface RecipeRepository {
    suspend fun getRecipeDetail(id: Int): Result<DetailedRecipe, String>
    suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>): Result<String, String>
    suspend fun deleteRecipe(id: Int): Result<String, String>
    suspend fun deleteRecipes(ids: List<Int>): Result<String, String>
}

@Singleton
class RecipeRepositoryImpl @Inject constructor(application: Application): RecipeRepository {
    private val db = RecipeAppDatabase.getDatabase(application)
    private val recipeDetailDao = db.recipeDetailDao()

    override suspend fun getRecipeDetail(id: Int): Result<DetailedRecipe, String> {
        try {
            return withContext(Dispatchers.IO) {
                val recipe = recipeDetailDao.getRecipeDetail(id)
                if (recipe.recipe.id == 0 || recipe.ingredients.isEmpty() || recipe.instructions.isEmpty()) {
                    return@withContext Err("Null returned")
                }
                return@withContext Ok(recipe)
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    //追加
    override suspend fun addRecipe(recipe: Recipe, ingredients: List<RecipeIngredient>, instructions: List<Instruction>): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                recipeDetailDao.addRecipe(recipe, ingredients, instructions)
                return@withContext Ok("Success")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    //指定したIDを削除
    override suspend fun deleteRecipe(id: Int): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                // 他のお気に入りやメニューに含まれていると消せない
                // transactionは恐らく不要
                db.withTransaction {
                    recipeDetailDao.deleteRecipe(id)
                    recipeDetailDao.deleteInstructions(id)
                    recipeDetailDao.deleteIngredients(id)
                }
                return@withContext Ok("Success")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    override suspend fun deleteRecipes(ids: List<Int>): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                for (id in ids) {
                    // 個別にcatch
                    try {
                        db.withTransaction {
                            recipeDetailDao.deleteRecipe(id)
                            recipeDetailDao.deleteInstructions(id)
                            recipeDetailDao.deleteIngredients(id)
                        }
                    } catch (e: Exception) {
                    }
                }
                return@withContext Ok("Success")
            }
        } catch (e: Exception) {
            return Err(e.toString())
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

