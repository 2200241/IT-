package com.example.recipe_app.room.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.google.gson.Gson

@Entity(tableName = "Caches")
data class Cache(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "categoryId")val categoryId: Int,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "image")val image: String,
    @ColumnInfo(name = "thumb")val thumb: String,
    @ColumnInfo(name = "serving")val serving: Int,
    @ColumnInfo(name = "ingredients")val ingredients: List<Ingredient>,
    @ColumnInfo(name = "instructions")val instructions: List<Instruction>
    )

//roomに入らない型を変換
class RecipesTypeConverter {

    @TypeConverter
    fun fromIngredients(value: List<Ingredient>?) = Gson().toJson(value)

    @TypeConverter
    fun toIngredients(value: String) = Gson().fromJson(value, Array<Ingredient>::class.java).toList()

    @TypeConverter
    fun fromInstructions(value: List<Instruction>?) = Gson().toJson(value)

    @TypeConverter
    fun toInstructions(value: String) = Gson().fromJson(value, Array<Instruction>::class.java).toList()
}