package com.example.recipe_app.room.shoppinglist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.RecipeThumb
import com.example.recipe_app.room.*
import com.google.gson.Gson

@Entity(tableName = "ShoppingLists")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "recipes")val recipes: List<RecipeThumb> = emptyList()
)

//class ShoppingListTypeConverter {
//FavoriteMenuに記述
//    @TypeConverter
//    fun fromrecipes(value: List<RecipeThumb>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun torecipes(value: String) = Gson().fromJson(value, Array<RecipeThumb>::class.java).toList()
//}