package com.example.recipe_app.use_cases.shoppinglist

import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.repositories.ShoppingItemRepository
import com.example.recipe_app.repositories.ShoppingListRepository
import com.example.recipe_app.room.shoppinglist.ShoppingItem
import com.example.recipe_app.room.shoppinglist.ShoppingList
import com.example.recipe_app.use_cases.InvalidGetRecipesException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingItemsUseCase @Inject constructor(private val shoppingItemRepository: ShoppingItemRepository){

    @Throws(InvalidGetShoppingItemsException::class)
    suspend fun getShoppingItem(): Flow<List<ShoppingItem>> {

        var shoppingItem: Flow<List<ShoppingItem>> = shoppingItemRepository.getAllShoppingItems()

        shoppingItem.collect {
            if (it.isEmpty()) {
                throw InvalidGetShoppingItemsException("shoppingItem is empty")
            }
        }
        return shoppingItem
    }

    // サンプルデータ
    suspend fun setTestShoppingItemData(): Flow<List<ShoppingItem>> {
//        shoppingListRepository.deleteAllShoppingItems()
        val ingredient = Ingredient("test", "test")
        val shoppingItem = ShoppingItem(0, 0 ,0, "name","quantity", 0, false)
        shoppingItemRepository.addShoppingItem(shoppingItem)

        return shoppingItemRepository.getAllShoppingItems()
    }
}

class InvalidGetShoppingItemsException(message: String): Exception(message)

