package com.example.recipe_app.room.shoppinglist

import androidx.room.*
import com.example.recipe_app.data.Ingredient
import com.google.gson.Gson

@Entity(tableName = "ShoppingItems")

data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "shoppingListId")val shoppingListId: Int = 0,
    @ColumnInfo(name = "recipeId")val recipeId: Int = 0,
    @ColumnInfo(name = "ingredient")val Ingredient: Ingredient = Ingredient(),
    @ColumnInfo(name = "serving")val serving: Int = 0,
    @ColumnInfo(name = "isChecked")val isChecked: Boolean = false
)

class ShoppingItemTypeConverter {
    @TypeConverter
    fun fromIngredients(value: Ingredient) = Gson().toJson(value)

    @TypeConverter
    fun toIngredients(value: String) = Gson().fromJson(value, Ingredient::class.java)

}