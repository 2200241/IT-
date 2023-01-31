package com.example.recipe_app.use_cases.allergy

import com.example.recipe_app.repositories.AllergenRepository
import javax.inject.Inject

class CheckAllergyUseCase @Inject constructor(
    private val repository: AllergenRepository
) {

    suspend fun checkAllergy(id: Int, check: Boolean) {
        repository.checkAllergy(id, check)
    }
}