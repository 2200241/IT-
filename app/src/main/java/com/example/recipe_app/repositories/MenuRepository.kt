package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.*
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MenuRepository {
    fun getAllMenus(): Flow<Map<Int, List<RecipeWithoutCategory>>>
    fun getMenuDetail(id: Int): Flow<Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>>
    suspend fun addMenu(menu: Menu, recipes: List<Recipe>, recipeIngredients: List<RecipeIngredient>)
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
    override suspend fun addMenu(menu: Menu, recipes: List<Recipe>, recipeIngredients: List<RecipeIngredient>) {
        withContext(Dispatchers.IO){
            val id = menuDao.addMenu(menu, recipes, recipeIngredients)
            menuDao.addShoppingItems(
                recipeIngredients.map { ShoppingItem(0, id.toInt(), it.id, false ) }
            )
        }
    }

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

