package com.example.recipe_app.use_cases.shoppinglist

import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.repositories.ShoppingListRepository
import javax.inject.Inject

class AddShoppingListUseCase @Inject constructor(
    private val repository: ShoppingListRepository
) {

    @Throws(InvalidAddFavoriteException::class)
    suspend fun addShoppingList(recipes: List<RecipeThumb>) {
        //データ削除 これは最後に消す
//        repository.deleteAllFavoriteRecipes()

        //エラー処理
        if (recipes.toString().isBlank()) {
            throw InvalidAddFavoriteException("The recipe_id of the note can't be empty.")
        }
        if (recipes.toString().isBlank()) {
            throw InvalidAddFavoriteException("The name of the note can't be empty.")
        }

        return repository.addShoppingList(recipes)
    }
}


class InvalidAddFavoriteException(message: String): Exception(message)
