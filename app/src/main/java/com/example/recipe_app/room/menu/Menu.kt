package com.example.recipe_app.room.menu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.ShoppingItem
import com.google.gson.Gson

@Entity(tableName = "Menus")
data class Menu(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "recipeIds")val recipeIds: List<Int>,	//取り出すときはRecipeThumbのリストで返す
    @ColumnInfo(name = "shoppingItems")val shoppingItems: List<ShoppingItem>
)

//roomに入らない型を変換
class MenusTypeConverter {

    @TypeConverter
    fun fromRecipeIds(value: List<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun toRecipeIds(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun fromShoppingItems(value: List<ShoppingItem>?) = Gson().toJson(value)

    @TypeConverter
    fun toShoppingItems(value: String) = Gson().fromJson(value, Array<ShoppingItem>::class.java).toList()
}
