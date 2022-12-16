package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.shoppingitem.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingItemRepository @Inject constructor(application: Application){

    private val shoppingItemDao = RecipeAppDatabase.getDatabase(application).shoppingItemDao()

    //全件取得
    fun getAllShoppingItems() = shoppingItemDao.getAllShoppingItems()

    //追加
    suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
        withContext(Dispatchers.IO){
            shoppingItemDao.addShoppingItem(shoppingItem)
        }
    }

    //指定したIDを削除
    suspend fun deleteShoppingItem(id: Int){
        withContext(Dispatchers.IO) {
            shoppingItemDao.deleteShoppingItem(id)
        }
    }

    //更新
    suspend fun updateShoppingItem(shoppingItem: ShoppingItem){
        withContext(Dispatchers.IO) {
            shoppingItemDao.updateShoppingItem(shoppingItem)
        }
    }

    //全件削除
    suspend fun deleteAllShoppingItems(){
        withContext(Dispatchers.IO) {
            shoppingItemDao.deleteAllShoppingItems()
        }
    }
}
