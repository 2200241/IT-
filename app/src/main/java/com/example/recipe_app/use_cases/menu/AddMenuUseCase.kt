package com.example.recipe_app.use_cases.menu


import com.example.recipe_app.repositories.MenuRepository
import com.example.recipe_app.room.menu.Menu
import javax.inject.Inject

class AddMenuUseCase @Inject constructor(
    private val repository: MenuRepository
) {

    @Throws(InvalidAddMenuException::class)
    suspend fun addFavoriteMenu(menu: Menu) {

        //エラー処理
        if(menu.id.toString().isBlank()) {
            throw InvalidAddMenuException("The id of the note can't be empty.")
        }

        repository.addMenu(menu)
    }
}


class InvalidAddMenuException(message: String): Exception(message)
