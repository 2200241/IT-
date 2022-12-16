package com.example.recipe_app.use_cases

import android.util.Log
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.repositories.RecipeRepository
import com.example.recipe_app.repositories.ShoppingItemRepository
import com.example.recipe_app.room.recipe.Recipe
import com.example.recipe_app.room.shoppingitem.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingItemsUseCase @Inject constructor(private val shoppingItemRepository: ShoppingItemRepository){

    @Throws(InvalidGetRecipesException::class)
    suspend fun getShoppingItem(): Flow<List<ShoppingItem>> {

        var shoppingItemList: Flow<List<ShoppingItem>> = shoppingItemRepository.getAllShoppingItems()

        shoppingItemList.collect {
            if (it.isEmpty()) {
                throw InvalidGetShoppingItemsException("recipeList is empty")
            }
        }
        return shoppingItemList
    }

    // サンプルデータ
    suspend fun setTestShoppingItemData(): Flow<List<ShoppingItem>> {
        shoppingItemRepository.deleteAllShoppingItems()
        val ingredient = ArrayList<Ingredient>()
        ingredient.add(Ingredient("name", "quantity"))
        val shoppingItem = ShoppingItem(
            0,
            ingredient[0],
            1,
            false
        )
        shoppingItemRepository.addShoppingItem(shoppingItem)

        return shoppingItemRepository.getAllShoppingItems()
    }
}

class InvalidGetShoppingItemsException(message: String): Exception(message)

