package com.example.recipe_app.repositories

import android.util.Log
import com.example.recipe_app.data.*
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

interface ApiRepository {
//    suspend fun fetchRecipeById(recipeId: Int): Result<MutableMap<Recipe, List<Ingredient>>, String>
    suspend fun fetchRecipeById(recipeId: Int): Result<RecipeBase, String>
    suspend fun fetchRecipes(conditions: String): Result<List<RecipeWithCategory>, String>
    suspend fun fetchSuggestions(keyword: String, target: String): Result<Map<Int, String>, String>
}

class ApiRepositoryImpl @Inject constructor(): ApiRepository {
    private val baseUrl = "http://54.166.77.47/api"

    override suspend fun fetchRecipeById(recipeId: Int): Result<RecipeBase, String> {
        if (recipeId < 0) return Err("Invalid ID")

        val url = "${baseUrl}/recipe/?id=$recipeId"

        try {
            return withContext(Dispatchers.IO) {
                val urlObj = URL(url)

                // アクセスしたAPIから情報を取得
                //テキストファイルを読み込むクラス(文字コードを読めるようにする準備(URLオブジェクト))
                val br = BufferedReader(InputStreamReader(urlObj.openStream()))

                //読み込んだデータを文字列に変換して代入
                val httpResult = br.readText()
                // 配列で返さない
                val obj = JSONObject(httpResult)

                val id = obj.getString("id").toInt()
                val categoryId = obj.getString("category_id").toInt()
                val title = obj.getString("title")
                val image = obj.getString("image")
                val servings = obj.getString("servings").toInt()

                val ingredients = mutableListOf<Ingredient>()
                val instructions = mutableListOf<String>()
                for (j in 0 until obj.getJSONArray("ingredients").length()) {
                    val name = obj.getJSONArray("ingredients").getJSONObject(j).getString("name")
                    val quantity = obj.getJSONArray("ingredients").getJSONObject(j).getString("quantity")
                    ingredients.add(Ingredient(name, quantity))
                }
                for (j in 0 until obj.getJSONArray("instructions").length()) {
                    val content = obj.getJSONArray("instructions").getJSONObject(j).getString("content")
                    instructions.add(content)
                }

                val recipe = RecipeBase(id, categoryId, title, image, servings, ingredients, instructions)
                return@withContext Ok(recipe)
            }
        } catch (e: Error) {
            return Err(e.toString())
        }
    }

    override suspend fun fetchRecipes(
        conditions: String
//        category_id: Int?,
//        title: String?,
//        description: String?,
//        servings: Int?,
//        ingredients: List<Int>?,
//        allergens: List<Int>?,
//        tags: List<Int>?
    ): Result<List<RecipeWithCategory>, String> {
        if (conditions.isBlank()) return Err("条件が指定されていません")

        try {
            return withContext(Dispatchers.IO) {
                val url = "${baseUrl}/recipes/?${conditions}"

//            if (category_id != null) url += "category_id=${category_id}&"
//            if (!title.isNullOrBlank()) url += "title=${title}&"
//            if (!description.isNullOrBlank()) url += "description=${description}&"
//            if (servings != null) url += "servings=${servings}&"
//            if (!ingredients.isNullOrEmpty()) url += "ingredients=" + ingredients.joinToString(",") + "&"
//            if (!allergens.isNullOrEmpty()) url += "allergens=" + allergens.joinToString(",") + "&"
//            if (!tags.isNullOrEmpty()) url += "tags=" + tags.joinToString(",")

                val urlObj = URL(url)

                // アクセスしたAPIから情報を取得
                //テキストファイルを読み込むクラス(文字コードを読めるようにする準備(URLオブジェクト))
                val br = BufferedReader(InputStreamReader(urlObj.openStream()))

                //読み込んだデータを文字列に変換して代入
                val httpResult = br.readText()
                val array = JSONArray(httpResult)

                if (array.length() < 1) return@withContext Err("No result")

                //レシピデータをListに格納
                val recipes = mutableListOf<RecipeWithCategory>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val id = obj.getString("id").toInt()
                    val categoryId = obj.getString("category_id").toInt()
                    val title = obj.getString("title")
                    val image = obj.getString("image")

                    recipes += RecipeWithCategory(id, categoryId, title, image)
                }
                return@withContext Ok(recipes)
            }
        } catch (e: Error) {
            return Err(e.toString())
        }
    }

    override suspend fun fetchSuggestions(keyword: String, target: String): Result<Map<Int, String>, String> {
        if (keyword.isBlank()) return Err("条件が指定されていません")

        try {
            return withContext(Dispatchers.IO) {
                val url = "${baseUrl}/${target}/?keyword=${keyword}"

                val urlObj = URL(url)

                // アクセスしたAPIから情報を取得
                //テキストファイルを読み込むクラス(文字コードを読めるようにする準備(URLオブジェクト))
                val br = BufferedReader(InputStreamReader(urlObj.openStream()))

                //読み込んだデータを文字列に変換して代入
                val httpResult = br.readText()
                val array = JSONArray(httpResult)

                val items = mutableMapOf<Int, String>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    val id = obj.getString("id").toInt()
                    val name = obj.getString("name")

                    items[id] = name
                }
                return@withContext Ok(items)
            }
        } catch (e: Error) {
            return Err(e.toString())
        }
    }
}




