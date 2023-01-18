package com.example.recipe_app.use_cases.shoppinglist

import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.repositories.ShoppingListRepository
import com.example.recipe_app.room.shoppinglist.ShoppingList
import com.example.recipe_app.use_cases.InvalidGetRecipesException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListsUseCase @Inject constructor(private val shoppingListRepository: ShoppingListRepository){

    @Throws(InvalidGetRecipesException::class)
    suspend fun getShoppingList(): Flow<List<ShoppingList>> {

        var shoppingList: Flow<List<ShoppingList>> = shoppingListRepository.getAllShoppingLists()

        shoppingList.collect {
            if (it.isEmpty()) {
                throw InvalidGetShoppingItemsException("shoppingList is empty")
            }
        }
        return shoppingList
    }

    // サンプルデータ
    suspend fun setTestShoppingListData(): Flow<List<ShoppingList>> {
//        shoppingListRepository.deleteAllShoppingItems()
        val recipeThumb = ArrayList<RecipeThumb>()
//        val shoppingItem = ArrayList<shoppingItem>()
        val ingredient = Ingredient("test","test")
        recipeThumb.add(RecipeThumb(0, ""))
//        shoppingItem.add(shoppingItem(ingredient, 0, false))
        shoppingListRepository.addShoppingList(recipeThumb)

        return shoppingListRepository.getAllShoppingLists()
    }
}

class InvalidGetShoppingListsException(message: String): Exception(message)

