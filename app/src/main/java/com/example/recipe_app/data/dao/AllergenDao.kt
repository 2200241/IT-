package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.Allergen
import kotlinx.coroutines.flow.Flow

@Dao
interface AllergenDao {
    @Query("SELECT * FROM allergens")
    fun getAllAllergens(): Flow<List<Allergen>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) 
    suspend fun addAllergen(allergen: Allergen)

    @Query("UPDATE allergens SET is_checked = :isChecked WHERE id = :id")
    suspend fun checkAllergen(id: Int, isChecked: Boolean)

    @Query("Delete from allergens where id =:id")
    suspend fun deleteAllergen(id: Int)

    @Update
    suspend fun updateAllergen(Allergen: Allergen)

    @Query("delete from allergens")
    suspend fun deleteAllAllergens()

}
