package com.example.recipe_app.use_cases.shoppinglist

import com.example.recipe_app.repositories.ShoppingItemRepository
import com.example.recipe_app.room.shoppinglist.ShoppingItem
import javax.inject.Inject

class AddShoppingItemUseCase @Inject constructor(
    private val repository: ShoppingItemRepository
) {

    @Throws(InvalidAddShoppingItemException::class)
    suspend fun addShoppingItem(shoppingItem: ShoppingItem) {
        //データ削除 これは最後に消す
//        repository.deleteAllShoppingItem()

        //エラー処理
        if (shoppingItem.shoppingListId.toString().isBlank()) {
            throw InvalidAddFavoriteException("The shoppingListId of the note can't be empty.")
        }

        return repository.addShoppingItem(shoppingItem)
    }
}


class InvalidAddShoppingItemException(message: String): Exception(message)
