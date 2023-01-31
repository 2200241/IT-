package com.example.recipe_app.room.allergy

import androidx.room.*
import com.example.recipe_app.use_cases.allergy.CheckAllergyUseCase
import kotlinx.coroutines.flow.Flow

@Dao
interface AllergyDao {
    @Query("SELECT * FROM Allergies")
    fun getAllAllergies(): Flow<List<Allergy>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergy(allergy: Allergy)

    @Query("UPDATE Allergies SET isCheck = :isCheck WHERE id = :id")
    suspend fun checkAllergy(id: Int, isCheck: Boolean)

    @Query("Delete from Allergies where id =:id")
    suspend fun deleteAllergy(id: Int)

    @Update
    suspend fun updateAllergy(allergy: Allergy)

    @Query("delete from allergies")
    suspend fun deleteAllAllergies()

}
