package com.example.recipe_app.use_cases

//import com.example.recipe_app.data.ShoppingItem
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.data.ShoppingItem as shoppingItem
import com.example.recipe_app.repositories.ShoppingItemRepository
import com.example.recipe_app.room.shoppingitem.MenuWithRecipeThumb
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingItemsUseCase @Inject constructor(private val shoppingItemRepository: ShoppingItemRepository){

    @Throws(InvalidGetRecipesException::class)
    suspend fun getShoppingItem(): Flow<List<MenuWithRecipeThumb>> {

        var shoppingItemList: Flow<List<MenuWithRecipeThumb>> = shoppingItemRepository.getAllShoppingItems()

        shoppingItemList.collect {
            if (it.isEmpty()) {
                throw InvalidGetShoppingItemsException("recipeList is empty")
            }
        }
        return shoppingItemList
    }

    // サンプルデータ
    suspend fun setTestShoppingItemData(): Flow<List<MenuWithRecipeThumb>> {
        shoppingItemRepository.deleteAllShoppingItems()
        val recipeThumb = ArrayList<RecipeThumb>()
        val shoppingItem = ArrayList<shoppingItem>()
        val ingredient = Ingredient("test","test")
        recipeThumb.add(RecipeThumb(0, ""))
        shoppingItem.add(shoppingItem(ingredient, 0, false))
        shoppingItemRepository.addShoppingItem(MenuWithRecipeThumb(0, recipeThumb, shoppingItem))

        return shoppingItemRepository.getAllShoppingItems()
    }
}

class InvalidGetShoppingItemsException(message: String): Exception(message)

