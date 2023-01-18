package com.example.recipe_app.use_cases.allergy

import com.example.recipe_app.repositories.AllergyRepository
import javax.inject.Inject

class CheckAllergyUseCase @Inject constructor(
    private val repository: AllergyRepository
) {

    suspend fun isCheckAllergy(check: Boolean, id: Int) {

        repository.checkAllergy(check, id)
    }
}