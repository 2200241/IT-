package com.example.recipe_app.repositories

import com.example.recipe_app.data.Recipe
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import javax.inject.Inject

interface TestRepository {
    suspend fun fetchRecipes(conditions: String): Result<List<Recipe>, String>
}

class TestRepositoryImpl @Inject constructor(): TestRepository {
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
}