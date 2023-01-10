package com.example.recipe_app.room.shoppingitem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.data.ShoppingItem
import com.google.gson.Gson

@Entity(tableName = "ShoppingItems")
data class MenuWithRecipeThumb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "recipes")val recipes: List<RecipeThumb> = emptyList(),
    @ColumnInfo(name = "shoppingItems")val shoppingItems: List<ShoppingItem> = emptyList()
)

class MenuWithRecipeThumbTypeConverter {
//FavoriteMenuに記述
//    @TypeConverter
//    fun fromrecipes(value: List<RecipeThumb>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun torecipes(value: String) = Gson().fromJson(value, Array<RecipeThumb>::class.java).toList()

    @TypeConverter
    fun fromShoppingItem(value: List<ShoppingItem>?) = Gson().toJson(value)

    @TypeConverter
    fun toShoppingItem(value: String) = Gson().fromJson(value, Array<ShoppingItem>::class.java).toList()

}