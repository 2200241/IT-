package com.example.recipe_app.use_cases.favorite_menu

import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.example.recipe_app.room.favorite_menu.FavoriteMenu
import javax.inject.Inject

class AddFavoriteMenuUseCase @Inject constructor(
    private val repository: FavoriteMenuRepository
) {

    @Throws(InvalidAddFavoriteException::class)
    suspend fun addFavoriteMenu(favoriteMenu: FavoriteMenu) {

        //エラー処理
        if(favoriteMenu.id.toString().isBlank()) {
            throw InvalidAddFavoriteException("The id of the note can't be empty.")
        }

        repository.addFavoriteMenu(favoriteMenu)
    }
}


class InvalidAddFavoriteException(message: String): Exception(message)
