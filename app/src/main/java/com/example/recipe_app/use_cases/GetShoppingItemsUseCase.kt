package com.example.recipe_app.use_cases

import android.util.Log
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.repositories.RecipeRepository
import com.example.recipe_app.repositories.ShoppingItemRepository
import com.example.recipe_app.room.recipe.Recipe
import com.example.recipe_app.room.shoppingitem.ShoppingItem
import javax.inject.Inject

class GetShoppingItemsUseCase @Inject constructor(private val shoppingItemRepository: ShoppingItemRepository){

    private var shoppingItemList: List<ShoppingItem> = emptyList()

    @Throws(InvalidGetRecipesException::class)
    suspend fun getShoppingItem(): List<ShoppingItem> {

        shoppingItemList = shoppingItemRepository.getAllShoppingItems()

        if (shoppingItemList == null) {
            throw InvalidGetShoppingItemsException("recipeList is null")
        }
        if (shoppingItemList.first().id.toString().isBlank()) {
            throw InvalidGetShoppingItemsException("お気に入りがありません")
        }

        return shoppingItemList
    }

    // サンプルデータ
    suspend fun setTestShoppingItemData(): List<ShoppingItem> {
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

