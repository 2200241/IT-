package com.example.recipe_app.room.favorite_recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.MenuWithoutIngredients
import com.example.recipe_app.data.RecipeWithCategoryId
import com.google.gson.Gson

@Entity(tableName = "FavoriteRecipes")
data class FavoriteRecipe(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "recipeId")val recipeId: Int = 0,
    @ColumnInfo(name = "categoryId")val categoryId: Int = 0,
    @ColumnInfo(name = "title")val title: String = "",
//    @ColumnInfo(name = "image")val image: String = "",
    @ColumnInfo(name = "thumb")val thumb: String = ""
)