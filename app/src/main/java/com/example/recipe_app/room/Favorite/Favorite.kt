package com.example.recipe_app.room.Favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true) val favorite_id: Int,
    @ColumnInfo(name = "recepi_id") val recepi_id: Int,
    @ColumnInfo(name = "name") val name: String?
)