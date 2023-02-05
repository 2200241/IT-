package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.Allergen
import kotlinx.coroutines.flow.Flow

@Dao
interface AllergenDao {
    @Query("SELECT * FROM allergens")
    fun getAllAllergens(): Flow<List<Allergen>>

    @Query("SELECT id FROM allergens WHERE is_checked = 1")
    fun getCheckedAllergens(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergen(allergen: Allergen)

    @Query("UPDATE allergens SET is_checked = :isChecked WHERE id = :id")
    suspend fun checkAllergen(id: Int, isChecked: Boolean)

    @Query("DELETE FROM allergens WHERE id =:id")
    suspend fun deleteAllergen(id: Int)

    @Update
    suspend fun updateAllergen(Allergen: Allergen)

    @Query("DELETE FROM allergens")
    suspend fun deleteAllAllergens()

}
