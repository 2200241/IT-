package com.example.recipe_app.room.recipe

import androidx.room.*
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.google.gson.Gson

@Entity(tableName = "Recipes")
data class Recipe(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "categoryId")val categoryId: Int = 0,
    @ColumnInfo(name = "title")val title: String = "",
    @ColumnInfo(name = "image")val image: String = "",
    @ColumnInfo(name = "thumb")val thumb: String = "",
    @ColumnInfo(name = "serving")val serving: Int = 0,
    @ColumnInfo(name = "ingredients")val ingredients: List<Ingredient> = emptyList(),
    @ColumnInfo(name = "instructions")val instructions: List<Instruction> = emptyList()
)

//roomに入らない型を変換
class RecipesTypeConverter {

    @TypeConverter
    fun fromIngredientsList(value: List<Ingredient>?) = Gson().toJson(value)

    @TypeConverter
    fun toIngredientsList(value: String) = Gson().fromJson(value, Array<Ingredient>::class.java).toList()

    @TypeConverter
    fun fromInstructionsList(value: List<Instruction>?) = Gson().toJson(value)

    @TypeConverter
    fun toInstructionsList(value: String) = Gson().fromJson(value, Array<Instruction>::class.java).toList()
}
