package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.shoppinglist.ShoppingList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingListRepository @Inject constructor(application: Application){

    private val shoppingListDao = RecipeAppDatabase.getDatabase(application).shoppingListDao()

    //全件取得
    fun getAllShoppingLists() = shoppingListDao.getAllShoppingLists()

    fun getAllShoppingListMaxId() = shoppingListDao.getShoppingListMaxId()

    //追加
    suspend fun addShoppingList(recipes: List<RecipeThumb>) {
        return withContext(Dispatchers.IO) {
            shoppingListDao.addShoppingList(recipes)
        }
    }

    //指定したIDを削除
    suspend fun deleteShoppingItem(id: Int){
        withContext(Dispatchers.IO) {
            shoppingListDao.deleteShoppingList(id)
        }
    }

    //更新
    suspend fun updateShoppingItem(shoppingList: ShoppingList){
        withContext(Dispatchers.IO) {
            shoppingListDao.updateShoppingList(shoppingList)
        }
    }

    //全件削除
    suspend fun deleteAllShoppingItems(){
        withContext(Dispatchers.IO) {
            shoppingListDao.deleteAllShoppingLists()
        }
    }
}
