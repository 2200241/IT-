package com.example.recipe_app.room.Favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val favorite_id: Int,
    @ColumnInfo(name = "recipe_id") val recipe_id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "img_url") val img_url: String
)