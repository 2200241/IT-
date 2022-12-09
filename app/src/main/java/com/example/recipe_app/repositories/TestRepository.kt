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
    suspend fun fetchRecipes(conditions: String): Result<List<RecipeWithCategoryId>, String>
    suspend fun fetchRecipeDetail(id: Int): Result<Recipe, String>
    fun fetchFavorites(): Flow<Result<Favorites, String>>
    suspend fun addFavoriteRecipe(recipeWithCategoryId: RecipeWithCategoryId): Result<String, String>
    suspend fun removeFavoriteRecipe(id: Int): Result<String, String>
    suspend fun addFavoriteMenu(menuWithoutIngredients: MenuWithoutIngredients): Result<String, String>
    suspend fun removeFavoriteMenu(id: Int): Result<String, String>
    fun fetchMenus():  Flow<Result<List<MenuWithoutIngredients>, String>>
    fun fetchMenu(id: Int): Flow<Result<MenuWithRecipeThumbs, String>>
    suspend fun checkShoppingListItem(id: Int, index: Int): Result<String, String>
    suspend fun addMenu(): Result<String, String>
    suspend fun removeMenu(id: Int): Result<String, String>
    fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>>
    suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String>
    suspend fun removeFromTempMenu(id: Int): Result<String, String>
    suspend fun removeAllFromTempMenu(): Result<String, String>
//    suspend fun getAllergens(): Flow<Result<String, String>>
//    suspend fun addAllergen(id: String): Result<String, String>
//    suspend fun removeAllergen(id: String): Result<String, String>
}

private var testMenuWithRecipeThumbs = emptyList<MenuWithRecipeThumbs>()
private var testFavorites = Favorites()
private var testTempMenu = emptyList<RecipeThumb>()

class TestRepositoryImpl @Inject constructor(): TestRepository {

    override suspend fun fetchRecipes(conditions: String): Result<List<RecipeWithCategoryId>, String> {
        val list = conditions.split("&").map { it }
        val a = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 1, title = "タイトルA@$it", thumb = "url@$it") }
        val b = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 2, title = "タイトルB@it", thumb = "url@$it") }
        val c = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 3, title = "タイトルC@it", thumb = "url@$it") }
        val d = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 4, title = "タイトルD@it", thumb = "url@$it") }
        val e = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 5, title = "タイトルE@it", thumb = "url@$it") }
        val f = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 6, title = "タイトルF@it", thumb = "url@$it") }
        val g = list.map { RecipeWithCategoryId(id = (1..999999).random(), categoryId = 7, title = "タイトルG@it", thumb = "url@$it") }

        delay(1000)
        return Ok(a + b + c + d + e + f + g)
    }

    override suspend fun fetchRecipeDetail(id: Int): Result<Recipe, String> {
        return Ok(
            Recipe(
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

    override suspend fun addFavoriteRecipe(recipeWithCategoryId: RecipeWithCategoryId): Result<String, String> {
        testFavorites = testFavorites.copy(recipeWithCategoryIds = testFavorites.recipeWithCategoryIds + recipeWithCategoryId)
        return Ok("Successed")
    }

    override suspend fun removeFavoriteRecipe(id: Int): Result<String, String> {
        testFavorites = testFavorites.copy(recipeWithCategoryIds = testFavorites.recipeWithCategoryIds.filter { it.id != id })
        return Ok("Successed")
    }

    override suspend fun addFavoriteMenu(menuWithoutIngredients: MenuWithoutIngredients): Result<String, String> {
        testFavorites = testFavorites.copy(menuWithoutIngredients = testFavorites.menuWithoutIngredients + menuWithoutIngredients)
        return Ok("Successed")
    }

    override suspend fun removeFavoriteMenu(id: Int): Result<String, String> {
        testFavorites = testFavorites.copy(menuWithoutIngredients = testFavorites.menuWithoutIngredients.filter { it.id != id })
        return Ok("Successed")
    }

    override fun fetchMenus(): Flow<Result<List<MenuWithoutIngredients>, String>> {
        return flow {
            while (true) {
                delay(500)
                emit(Ok(testMenuWithRecipeThumbs.map {
                    MenuWithoutIngredients(
                        id = it.id,
                        recipes = it.recipes
                    )
                }))
            }
        }
    }

    override fun fetchMenu(id: Int): Flow<Result<MenuWithRecipeThumbs, String>> {
        return flow {
            while (true) {
                delay(500)
                emit(
                    if (testMenuWithRecipeThumbs.find { it.id == id } != null) {
                        Ok(testMenuWithRecipeThumbs.find { it.id == id }!!)
                    } else {
                        Err("The menu is not found.")
                    }
                )
            }
        }
    }

    override suspend fun checkShoppingListItem(id: Int, index: Int): Result<String, String> {
        testMenuWithRecipeThumbs.forEach { menu ->
            if (menu.id == id) {
                menu.shoppingItems.forEachIndexed { i, shoppingItem ->
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
            testMenuWithRecipeThumbs += MenuWithRecipeThumbs(
                id = (1..99999).random(),
                recipes = testTempMenu,
                shoppingItems = withIngredients
            )
            return Ok("Successed")
        }
    }

    override suspend fun removeMenu(id: Int): Result<String, String> {
        testMenuWithRecipeThumbs = testMenuWithRecipeThumbs.filter { it.id != id }
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

    override suspend fun removeFromTempMenu(id: Int): Result<String, String> {
        testTempMenu = testTempMenu.filter { it.id != id }
        return Ok("Successed")
    }

    override suspend fun removeAllFromTempMenu(): Result<String, String> {
        testTempMenu = emptyList()
        return Ok("Successed")
    }
}