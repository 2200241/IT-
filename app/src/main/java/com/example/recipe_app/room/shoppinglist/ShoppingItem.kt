package com.example.recipe_app.room.shoppinglist

import androidx.room.*
import com.example.recipe_app.data.Ingredient
import com.google.gson.Gson

@Entity(tableName = "ShoppingItems")

data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "shoppingListId")val shoppingListId: Int = 0,
    @ColumnInfo(name = "recipeId")val recipeId: Int = 0,
    @ColumnInfo(name = "name")val name: String= "",
    @ColumnInfo(name = "quantity")val quantity: String = "",
    @ColumnInfo(name = "serving")val serving: Int = 0,
    @ColumnInfo(name = "isChecked")val isChecked: Boolean = false
)