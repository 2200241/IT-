package com.example.recipe_app.room.Allergy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Allergies")
data class Allergy(
    @PrimaryKey(autoGenerate = true) val Allergy_id: Int,
    @ColumnInfo(name = "Allergy_name") val Allergy_name: String,
    @ColumnInfo(name = "Allergy_confirmation") val Allergy_confirmation: Boolean
)