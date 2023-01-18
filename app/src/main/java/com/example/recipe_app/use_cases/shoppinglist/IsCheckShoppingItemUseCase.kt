package com.example.recipe_app.use_cases.shoppinglist

import com.example.recipe_app.repositories.ShoppingItemRepository
import com.example.recipe_app.room.shoppinglist.ShoppingItem
import javax.inject.Inject

class IsCheckShoppingItemUseCase @Inject constructor(
    private val repository: ShoppingItemRepository
) {

    suspend fun isCheckShoppingItem(isChecked: Boolean, ShoppingItemListId: Int, id: Int) {

        repository.isCheckShoppingItem(isChecked, ShoppingItemListId, id)
    }
}