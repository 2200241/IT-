package com.example.recipe_app.use_cases.favorite_menu

import com.example.recipe_app.data.*
import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.example.recipe_app.room.favorite_menu.FavoriteMenu
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMenusUseCase @Inject constructor(favoriteMenuRepository: FavoriteMenuRepository){

    var favoriteMenuList: Flow<List<FavoriteMenu>> = favoriteMenuRepository.getAllFavoriteMenus()

    @Throws(InvalidGetFavoritesException::class)
    suspend fun getFavoriteMenu(): Flow<List<FavoriteMenu>> {

        favoriteMenuList.collect {
            if (it.isEmpty()) {
                throw InvalidGetFavoritesException("お気に入りがありません")
            }
        }

        return favoriteMenuList
    }
}

class InvalidGetFavoritesException(message: String): Exception(message)

