package com.example.recipe_app.room.allergy

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AllergyDao {
    @Query("SELECT * FROM allergies")
    fun getAllAllergies(): Flow<List<Allergy>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergy(allergy: Allergy)

    @Query("delete from allergies where allergy_id =:allergy_id")
    suspend fun deleteAllergy(allergy_id: Int)

    @Update
    suspend fun updateAllergy(allergy: Allergy)

    @Query("delete from allergies")
    suspend fun deleteAllAllergies()

}
