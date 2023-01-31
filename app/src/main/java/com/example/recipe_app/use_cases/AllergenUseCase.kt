package com.example.recipe_app.use_cases

import com.example.recipe_app.data.Allergen
import com.example.recipe_app.repositories.AllergenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AllergenUseCase {
    fun getAllergens(): Flow<List<Allergen>>
    suspend fun checkAllergen(id: Int, isChecked: Boolean)
}

class AllergenUseCaseImpl @Inject constructor(
    private val repository: AllergenRepository
): AllergenUseCase {
    override fun getAllergens() = repository.getAllAllergens()

    override suspend fun checkAllergen(id: Int, isChecked: Boolean) {
        repository.checkAllergen(id, isChecked)
    }
}

