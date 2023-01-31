package com.example.recipe_app.use_cases.allergy

import com.example.recipe_app.repositories.AllergyRepository
import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.example.recipe_app.room.allergy.Allergy
import com.example.recipe_app.room.favorite_menu.FavoriteMenu
import javax.inject.Inject

class AddAllergyUseCase @Inject constructor(
    private val repository: AllergyRepository
) {

    @Throws(InvalidAddAllergyException::class)
    suspend fun addAllergy(allergy: Allergy) {
        //エラー処理
        if(allergy.id.toString().isBlank()) {
            throw InvalidAddAllergyException("The id of the note can't be empty.")
        }

        repository.addAllergy(allergy)
    }
}


class InvalidAddAllergyException(message: String): Exception(message)
