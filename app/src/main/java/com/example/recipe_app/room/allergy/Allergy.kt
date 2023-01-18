package com.example.recipe_app.room.allergy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Allergies")
data class Allergy(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "check") val check: Boolean = false
)