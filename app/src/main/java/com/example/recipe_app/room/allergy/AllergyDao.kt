package com.example.recipe_app.room.allergy

import androidx.room.*

@Dao
interface AllergyDao {
    @Query("SELECT * FROM allergies")
    suspend fun getAllAllergies(): List<Allergy>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergy(allergy: Allergy)

    @Query("delete from allergies where allergy_id =:allergy_id")
    suspend fun deleteAllergy(allergy_id: Int)

    @Update
    suspend fun updateAllergy(allergy: Allergy)

    @Query("delete from allergies")
    suspend fun deleteAllAllergies()

}
