package com.example.recipe_app.room.favorite_menu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.RecipeThumb
import com.google.gson.Gson

@Entity(tableName = "FavoriteMenus")
data class FavoriteMenu(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo val recipes: List<RecipeThumb> = emptyList()
)

//roomに入らない型を変換
class FavoriteMenusTypeConverter {

    @TypeConverter
    fun fromRecipeThumb(value: List<RecipeThumb>?) = Gson().toJson(value)

    @TypeConverter
    fun toRecipeThumb(value: String) = Gson().fromJson(value, Array<RecipeThumb>::class.java).toList()
}
