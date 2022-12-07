package com.example.recipe_app.repositories

import com.example.recipe_app.data.*
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
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
    fun fetchMenus():  Flow<Result<List<Menu>, String>>
    fun fetchMenu(id: String): Flow<Result<MenuDetail, String>>
    suspend fun checkShoppingListItem(id: String, index: Int): Result<String, String>
    suspend fun addMenu(): Result<String, String>
    suspend fun removeMenu(id: String): Result<String, String>
    fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>>
    suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String>
    suspend fun removeFromTempMenu(id: String): Result<String, String>
    suspend fun removeAllFromTempMenu(): Result<String, String>
//    suspend fun getAllergens(): Flow<Result<String, String>>
//    suspend fun addAllergen(id: String): Result<String, String>
//    suspend fun removeAllergen(id: String): Result<String, String>
}

private var testMenus = emptyList<MenuDetail>()
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
            serving = (1..5).random(),
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

    override fun fetchMenus(): Flow<Result<List<Menu>, String>> {
        return flow {
            delay(500)
            emit(Ok(testMenus.map { it.menu }))
        }
    }

    override fun fetchMenu(id: String): Flow<Result<MenuDetail, String>> {
        return flow {
            delay(500)
            emit(
                if (testMenus.find { it.menu.id == id } != null) {
                    Ok(testMenus.find { it.menu.id == id }!!)
                } else {
                    Err("The menu is not found.")
                }
            )
        }
    }

    override suspend fun checkShoppingListItem(id: String, index: Int): Result<String, String> {
        testMenus.forEach { menuDetail ->
            if (menuDetail.menu.id == id) {
                menuDetail.shoppingItems.forEachIndexed { i, shoppingItem ->
                    if (i == index) !shoppingItem.isChecked
                }
            }
        }
        return Ok("Successed")
    }

    override suspend fun addMenu(): Result<String, String> {
        if (testTempMenu.isEmpty()) {
            return Err("No recipe is selected")
        } else {
            var withIngredients: List<ShoppingItem> = emptyList()
            testTempMenu.map {
                fetchRecipeDetail(it.id).mapBoth(
                    success = { recipe ->
                        recipe.ingredients.map { ingredient ->
                            withIngredients += ShoppingItem(ingredient, recipe.serving, false)
                        }
                    },
                    failure = {}
                )
            }
            testMenus += MenuDetail(
                menu = Menu(id = "test${Math.random()}", recipes = testTempMenu),
                shoppingItems = withIngredients
            )
            return Ok("Successed")
        }
    }

    override suspend fun removeMenu(id: String): Result<String, String> {
        testMenus = testMenus.filter { it.menu.id != id }
        return Ok("Successed")
    }

    override fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>> {
        return flow {
            while (true) {
                delay(500)
                emit(Ok(testTempMenu))
            }
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