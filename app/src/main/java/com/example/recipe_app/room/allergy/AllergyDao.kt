package com.example.recipe_app.room.allergy

import androidx.room.*
import com.example.recipe_app.use_cases.allergy.CheckAllergyUseCase
import kotlinx.coroutines.flow.Flow

@Dao
interface AllergyDao {
    @Query("SELECT * FROM allergies")
    fun getAllAllergies(): Flow<List<Allergy>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergy(allergy: Allergy)


    @Query("UPDATE Allergies SET `check` = :check WHERE id = :id")
    suspend fun checkAllergy(check: Boolean, id: Int)

    @Query("delete from allergies where id =:id")
    suspend fun deleteAllergy(id: Int)

    @Update
    suspend fun updateAllergy(allergy: Allergy)

    @Query("delete from allergies")
    suspend fun deleteAllAllergies()

}
