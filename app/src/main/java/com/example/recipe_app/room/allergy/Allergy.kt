package com.example.recipe_app.room.allergy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Allergies")
data class Allergy(
    @PrimaryKey val Allergy_id: Int = 0,
    @ColumnInfo(name = "Allergy_name") val Allergy_name: String = "",
    @ColumnInfo(name = "Allergy_check") val Allergy_check: Boolean = false
)