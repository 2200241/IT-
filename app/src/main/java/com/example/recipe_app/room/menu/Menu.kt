package com.example.recipe_app.room.menu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipe_app.data.RecipeThumb

@Entity(tableName = "Menus")
data class Menu(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo val recipes: List<RecipeThumb> = emptyList()
)

