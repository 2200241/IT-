package com.example.recipe_app.use_cases.allergy

import javax.inject.Inject

data class AllergyUseCases @Inject constructor(
    val addAllergy: AddAllergyUseCase,
    val getAllergy: GetAllergyUseCase,
    val checkAllergy: CheckAllergyUseCase,
)

