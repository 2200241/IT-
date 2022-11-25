package com.example.recipe_app.repositories

import com.example.recipe_app.data.Favorites
import com.example.recipe_app.data.Menu
import com.example.recipe_app.data.Recipe
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

interface TestRepository {
    suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String>
    suspend fun fetchFavorites(): Result<Favorites, String>
    suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String>
    suspend fun addFavoriteMenu(menu: Menu): Result<String, String>
    suspend fun fetchMenus(): Result<List<Menu>, String>
    suspend fun addMenu(menu: Menu): Result<String, String>
}

class TestRepositoryImpl @Inject constructor(): TestRepository {
    private var testMenus = emptyList<Menu>()
    private var testFavorites = Favorites()

    override suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String> {
        val list = conditions.split("&")
        val a = list.map { Recipe(id = it, title = "タイトルA@$it", thumb = "http://hoge/thumb/$it.png") }
        val b = list.map { Recipe(id = it, title = "タイトルB@$it", thumb = "http://hoge/thumb/$it.png") }
        val c = list.map { Recipe(id = it, title = "タイトルC@$it", thumb = "http://hoge/thumb/$it.png") }
        val d = list.map { Recipe(id = it, title = "タイトルD@$it", thumb = "http://hoge/thumb/$it.png") }
        val e = list.map { Recipe(id = it, title = "タイトルE@$it", thumb = "http://hoge/thumb/$it.png") }

        delay(1000)
        return Ok(a + b + c + d + e)
    }

    override suspend fun fetchFavorites(): Result<Favorites, String> {
        delay(1000)
        return Ok(testFavorites)
    }

    override suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String> {
        testFavorites.recipes + recipe
        return Ok("Successed")
    }

    override suspend fun addFavoriteMenu(menu: Menu): Result<String, String> {
        testFavorites.menus + menu
        return Ok("Successed")
    }

    override suspend fun fetchMenus(): Result<List<Menu>, String> {
        return Ok(testMenus)
    }

    override suspend fun addMenu(menu: Menu): Result<String, String> {
        testMenus + menu
        return Ok("Successed")
    }
}