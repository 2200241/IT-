package com.example.recipe_app.repositories

import android.util.Log
import com.example.recipe_app.data.*
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface TestRepository {
    suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String>
    suspend fun fetchRecipeDetail(id: String): Result<RecipeDetail, String>
    fun fetchFavorites(): Flow<Result<Favorites, String>>
    suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String>
    suspend fun removeFavoriteRecipe(id: String): Result<String, String>
    suspend fun addFavoriteMenu(menu: Menu): Result<String, String>
    suspend fun removeFavoriteMenu(id: String): Result<String, String>
    suspend fun fetchMenus():  Flow<Result<List<Menu>, String>>
    suspend fun fetchMenu(id: String): Result<List<RecipeThumb>, String>
    suspend fun fetchShoppingList(id: String): Result<List<Ingredient>, String>
    suspend fun addMenu(menu: Menu): Result<String, String>
    suspend fun removeMenu(id: String): Result<String, String>
    suspend fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>>
    suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String>
    suspend fun removeFromTempMenu(id: String): Result<String, String>
    suspend fun removeAllFromTempMenu(): Result<String, String>
//    suspend fun getAllergens(): Flow<Result<String, String>>
//    suspend fun addAllergen(id: String): Result<String, String>
//    suspend fun removeAllergen(id: String): Result<String, String>
}

private var testMenus = emptyList<Menu>()
private var testFavorites = Favorites()
private var testTempMenu = emptyList<RecipeThumb>()

class TestRepositoryImpl @Inject constructor(): TestRepository {

    override suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String> {
        val list = conditions.split("&")
        val a = list.map { Recipe(id = it + "a", categoryId = 1, title = "タイトルA@$it", thumb = "url@$it") }
        val b = list.map { Recipe(id = it + "b", categoryId = 2, title = "タイトルB@$it", thumb = "url@$it") }
        val c = list.map { Recipe(id = it + "c", categoryId = 3, title = "タイトルC@$it", thumb = "url@$it") }
        val d = list.map { Recipe(id = it + "d", categoryId = 4, title = "タイトルD@$it", thumb = "url@$it") }
        val e = list.map { Recipe(id = it + "e", categoryId = 5, title = "タイトルE@$it", thumb = "url@$it") }
        val f = list.map { Recipe(id = it + "f", categoryId = 6, title = "タイトルF@$it", thumb = "url@$it") }
        val g = list.map { Recipe(id = it + "g", categoryId = 7, title = "タイトルG@$it", thumb = "url@$it") }

        delay(1000)
        return Ok(a + b + c + d + e + f + g)
    }

    override suspend fun fetchRecipeDetail(id: String): Result<RecipeDetail, String> {
        return Ok(
            RecipeDetail(
            id = id,
            title = "title$id",
            image = "url$id",
            ingredients = listOf(
                Ingredient(name = "卵", quantity = "2個"),
                Ingredient(name = "小麦粉", quantity = "大さじ2"),
                Ingredient(name = "水", quantity = "200ml")
            ),
            instructions = listOf(
                Instruction(order = 1, content = "手順１"),
                Instruction(order = 2, content = "手順２"),
                Instruction(order = 3, content = "手順３")
            )
        )
        )
    }

    override fun fetchFavorites(): Flow<Result<Favorites, String>> {
        return flow {
            while (true) {
                delay(500)
                emit(Ok(testFavorites))
            }
        }
    }

    override suspend fun addFavoriteRecipe(recipe: Recipe): Result<String, String> {
        testFavorites = testFavorites.copy(recipes = testFavorites.recipes + recipe)
        return Ok("Successed")
    }

    override suspend fun removeFavoriteRecipe(id: String): Result<String, String> {
        testFavorites = testFavorites.copy(recipes = testFavorites.recipes.filter { it.id != id })
        return Ok("Successed")
    }

    override suspend fun addFavoriteMenu(menu: Menu): Result<String, String> {
        testFavorites = testFavorites.copy(menus = testFavorites.menus + menu)
        return Ok("Successed")
    }

    override suspend fun removeFavoriteMenu(id: String): Result<String, String> {
        testFavorites = testFavorites.copy(menus = testFavorites.menus.filter { it.id != id })
        return Ok("Successed")
    }

    override suspend fun fetchMenus(): Flow<Result<List<Menu>, String>> {
        return flow {
            delay(500)
            emit(Ok(testMenus))
        }
    }

    override suspend fun fetchMenu(id: String): Result<List<RecipeThumb>, String> {
        return Ok(listOf(
            RecipeThumb(id = "1234567890", thumb = "url"),
            RecipeThumb(id = "0000000001", thumb = "url"),
            RecipeThumb(id = "9876543210", thumb = "url"),
            RecipeThumb(id = "7777777777", thumb = "url"),
            RecipeThumb(id = "5555555555", thumb = "url")
        ))
    }

    override suspend fun fetchShoppingList(id: String): Result<List<Ingredient>, String> {
        return Ok(listOf(
            Ingredient("小麦粉", "大さじ１"),
            Ingredient("水", "200ml"),
            Ingredient("卵", "1個"),
            Ingredient("豚肉", "100g"),
            Ingredient("サラダ油", "")
        ))
    }

    override suspend fun addMenu(menu: Menu): Result<String, String> {
        testMenus += menu
        return Ok("Successed")
    }

    override suspend fun removeMenu(id: String): Result<String, String> {
        testMenus = testMenus.filter { it.id != id }
        return Ok("Successed")
    }

    override suspend fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>> {
        return flow {
            delay(500)
            emit(Ok(testTempMenu))
        }
    }

    override suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String> {
        testTempMenu += recipe
        return Ok("Successed")
    }

    override suspend fun removeFromTempMenu(id: String): Result<String, String> {
        testTempMenu = testTempMenu.filter { it.id != id }
        return Ok("Successed")
    }

    override suspend fun removeAllFromTempMenu(): Result<String, String> {
        testTempMenu = emptyList()
        return Ok("Successed")
    }
}