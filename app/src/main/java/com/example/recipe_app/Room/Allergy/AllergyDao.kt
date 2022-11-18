package com.example.recipe_app.Room.Allergy

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.recipe_app.Room.Allergy.Allergy

@Dao
interface AllergyDao {
    @Query("SELECT * FROM allergies")
    fun readAllData(): LiveData<List<Allergy>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergy(allergy: Allergy)

    @Query("delete from allergies where allergy_id =:allergy_id")
    suspend fun deleteAllergy(allergy_id: Int)

    @Update
    suspend fun updateAllergy(allergy: Allergy)

    @Query("delete from allergies")
    suspend fun deleteAllAllergies()

}