package com.example.recipe_app.repositories

import android.app.Application
import androidx.room.withTransaction
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.combine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MenuRepository {
    fun getAllMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>
    fun getMenuDetail(id: Int): Flow<Pair<List<RecipeWithoutCategory>, List<ShoppingItemWithIngredient>>>
    suspend fun addMenu(recipes: List<DetailedRecipe>): Result<String, String>
    suspend fun deleteMenu(id: Int): Result<String, String>
    suspend fun updateMenu(menu: Menu): Result<String, String>
    suspend fun checkShoppingItem(id: Int): Result<String, String>
}

class MenuRepositoryImpl @Inject constructor(application: Application): MenuRepository {
    private val db = RecipeAppDatabase.getDatabase(application)
    private val menuDao = db.menuDao()

    override fun getAllMenus() = menuDao.getAllMenus()
    override fun getMenuDetail(id: Int): Flow<Pair<List<RecipeWithoutCategory>, List<ShoppingItemWithIngredient>>> {
        return combine(menuDao.getMenuRecipes(id), menuDao.getShoppingItems(id), ::Pair)
    }

    override suspend fun addMenu(recipes: List<DetailedRecipe>): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                db.withTransaction {
                    val ingredients = mutableListOf<RecipeIngredient>()
                    val instructions = mutableListOf<Instruction>()

                    recipes.forEach { it.ingredients.forEach { ing -> ingredients.add(ing) } }
                    recipes.forEach { it.instructions.forEach { ins -> instructions.add(ins) } }

                    menuDao.addRecipes(
                        recipes.map { it.recipe },
                        instructions
                    )
                    val id = menuDao.addMenu(Menu())
                    menuDao.addMenuRecipes(
                        recipes.map { MenuRecipe(0, id.toInt(), it.recipe.id) }
                    )
                    val ingIds = menuDao.addIngredients(ingredients)
                    val temp = mutableListOf<ShoppingItem>()
                    ingIds.forEach {
                        temp.add(ShoppingItem(
                                0,
                                id.toInt(),
                                it.toInt(),
                                false
                        ))
                    }
                    menuDao.addShoppingItems(temp)
                }
                return@withContext Ok("The menu added successfully")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    override suspend fun deleteMenu(id: Int): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                db.withTransaction {
                    menuDao.deleteMenuRecipes(id)
                    menuDao.deleteShoppingItems(id)
                    menuDao.deleteMenu(id)
                }
                return@withContext Ok("The menu deleted successfully")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    //更新
    override suspend fun updateMenu(menu: Menu): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                menuDao.updateMenu(menu)
                return@withContext Ok("The menu updated successfully")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

    override suspend fun checkShoppingItem(id: Int): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                menuDao.checkShoppingItem(id)
                return@withContext Ok("The item updated successfully")
            }
        } catch (e: Exception) {
            return Err(e.toString())
        }
    }

//    //全件削除
//    suspend fun deleteAllMenus(){
//        withContext(Dispatchers.IO) {
//            menuDao.deleteAllMenus()
//        }
//    }
}

