package com.example.recipe_app.use_cases.allergy

import com.example.recipe_app.repositories.AllergenRepository
import javax.inject.Inject

class AllergyUseCases @Inject constructor(
    private val repository: AllergenRepository
) {
    fun getAllergies() = repository.getAllAllergies()

    suspend fun checkAllergy(id: Int, isChecked: Boolean) {
        repository.checkAllergy(id, isChecked)
    }
}

