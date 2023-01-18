package com.example.recipe_app.use_cases.menu

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.MenuRepository
import com.example.recipe_app.room.menu.Menu
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenusUseCase @Inject constructor(menuRepository: MenuRepository){

    var menuList: Flow<List<Menu>> = menuRepository.getAllMenus()

    @Throws(InvalidGetMenusException::class)
    suspend fun getMenu(): Flow<List<Menu>> {

        menuList.collect {
            if (it.isEmpty()) {
                throw InvalidGetMenusException("お気に入りがありません")
            }
        }

        return menuList
    }
}

class InvalidGetMenusException(message: String): Exception(message)

