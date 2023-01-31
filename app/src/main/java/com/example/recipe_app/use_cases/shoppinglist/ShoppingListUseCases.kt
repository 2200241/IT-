package com.example.recipe_app.use_cases.shoppinglist

import javax.inject.Inject

data class ShoppingListUseCases @Inject constructor(
    val addShoppingList: AddShoppingListUseCase,
    val getShoppingLists: GetShoppingListsUseCase,
    val addShoppingItem: AddShoppingItemUseCase,
    val geShoppingItems: GetShoppingItemsUseCase,
    val isCheckShoppingItem: IsCheckShoppingItemUseCase
)

