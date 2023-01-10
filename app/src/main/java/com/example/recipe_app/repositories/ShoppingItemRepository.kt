package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.example.recipe_app.room.shoppingitem.MenuWithRecipeThumb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoppingItemRepository @Inject constructor(application: Application){

    private val menuWithRecipeThumbDao = RecipeAppDatabase.getDatabase(application).menuWithRecipeThumbDao()

    //全件取得
    fun getAllShoppingItems() = menuWithRecipeThumbDao.getAllShoppingItems()

    //追加
    suspend fun addShoppingItem(menuWithRecipeThumb: MenuWithRecipeThumb) {
        withContext(Dispatchers.IO){
            menuWithRecipeThumbDao.addShoppingItem(menuWithRecipeThumb)
        }
    }

    //指定したIDを削除
    suspend fun deleteShoppingItem(id: Int){
        withContext(Dispatchers.IO) {
            menuWithRecipeThumbDao.deleteShoppingItem(id)
        }
    }

    //更新
    suspend fun updateShoppingItem(menuWithRecipeThumb: MenuWithRecipeThumb){
        withContext(Dispatchers.IO) {
            menuWithRecipeThumbDao.updateShoppingItem(menuWithRecipeThumb)
        }
    }

    //全件削除
    suspend fun deleteAllShoppingItems(){
        withContext(Dispatchers.IO) {
            menuWithRecipeThumbDao.deleteAllShoppingItems()
        }
    }
}
