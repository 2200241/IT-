package com.example.recipe_app.room.shoppingitem

import android.net.MacAddress.fromString
import android.os.ParcelUuid.fromString
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.Ingredient
import com.google.gson.Gson
import java.net.HttpCookie.parse
import java.nio.file.attribute.PosixFilePermissions.fromString
import java.util.Locale.LanguageRange.parse
import java.util.UUID.fromString
import java.util.logging.Level.parse

@Entity(tableName = "ShoppingItems")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "ingredient")val ingredient: Ingredient,
    @ColumnInfo(name = "serving")val serving: Int,
    @ColumnInfo(name = "isChecked")val isChecked: Boolean
)

class ShoppingItemTypeConverter {

    @TypeConverter
    fun fromIngredient(ingredient: Ingredient) = Gson().toJson(ingredient)


    @TypeConverter
    fun toIngredient(ingredient: String) = Gson().fromJson(ingredient, Ingredient::class.java)



}