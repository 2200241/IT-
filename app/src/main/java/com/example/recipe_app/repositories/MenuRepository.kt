package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MenuRepository {
    fun getAllMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>
    fun getMenuDetail(id: Int): Flow<Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>>
    suspend fun addMenu(recipes: List<RecipeDetail>)
    suspend fun deleteMenu(id: Int)
    suspend fun updateMenu(menu: Menu)
    suspend fun checkShoppingItem(id: Int)
}

class MenuRepositoryImpl @Inject constructor(application: Application): MenuRepository {
    private val menuDao = RecipeAppDatabase.getDatabase(application).menuDao()

    //全件取得
    override fun getAllMenus() = menuDao.getAllMenus()
    override fun getMenuDetail(id: Int) = menuDao.getMenuDetail(id)

    //追加
    override suspend fun addMenu(recipes: List<RecipeDetail>) {
        withContext(Dispatchers.IO){
            val ingredients = emptyList<RecipeIngredient>()
            val instructions = emptyList<Instruction>()

            recipes.map { it.ingredients.map { ing -> ingredients.plus(ing) } }
            recipes.map { it.instructions.map { ins -> instructions.plus(ins) } }

            val id = menuDao.addMenu(Menu())
            menuDao.addRecipes(
                recipes.map { it.recipe },
                ingredients,
                instructions
            )

            menuDao.addMenuRecipes(
                recipes.map { MenuRecipe(0, id.toInt(), it.recipe.id) }
            )

            val temp = emptyList<ShoppingItem>()
            recipes.map { it.ingredients.map { temp.plus(ShoppingItem(0, id.toInt(), it.id, false )) } }
            menuDao.addShoppingItems(temp)
        }
    }

    // TODO
    //指定したIDを削除
    override suspend fun deleteMenu(id: Int){
        withContext(Dispatchers.IO) {
            menuDao.deleteMenu(id)
        }
    }

    //更新
    override suspend fun updateMenu(menu: Menu){
        withContext(Dispatchers.IO) {
            menuDao.updateMenu(menu)
        }
    }

    override suspend fun checkShoppingItem(id: Int) {
        withContext(Dispatchers.IO) {
            menuDao.checkShoppingItem(id)
        }
    }

//    //全件削除
//    suspend fun deleteAllMenus(){
//        withContext(Dispatchers.IO) {
//            menuDao.deleteAllMenus()
//        }
//    }
}

