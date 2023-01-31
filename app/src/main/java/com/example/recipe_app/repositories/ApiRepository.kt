package com.example.recipe_app.repositories

import android.util.Log
import com.example.recipe_app.data.*
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class ApiRepository @Inject constructor() {
    private val baseUrl = "http://54.166.77.47/api"

    //レシピIDから検索
    suspend fun searchRecipeId(recipeId: String) {
        recipeData("${baseUrl}/recipe/?id=$recipeId")
    }

    private suspend fun recipeData(url: String) {
        //Json格納
        var httpResult = ""

        val recipe: MutableMap<Recipe, List<Ingredient>> = mutableMapOf()

        withContext(Dispatchers.IO) {
            val urlObj = URL(url)

            // アクセスしたAPIから情報を取得
            //テキストファイルを読み込むクラス(文字コードを読めるようにする準備(URLオブジェクト))
            val br = BufferedReader(InputStreamReader(urlObj.openStream()))

            //読み込んだデータを文字列に変換して代入
            httpResult = br.readText()

            val jsonObj = JSONObject(httpResult)

            val obj =jsonObj.getJSONArray("data")

            //レシピデータをListに格納
            for(i in 0 until obj.length()) {

                val id = obj.getJSONObject(i).getString("id").toInt()
                val categoryId = obj.getJSONObject(i).getString("category_id").toInt()
                val title = obj.getJSONObject(i).getString("title")
                val image = obj.getJSONObject(i).getString("image")
                val servings = obj.getJSONObject(i).getString("servings").toInt()

                val ingredients =  mutableListOf<Ingredient>()
                val instructions = mutableListOf<String>()

                for (j in 0 until obj.getJSONObject(i).getJSONArray("ingredients").length()) {
                    val name = obj.getJSONObject(i).getJSONArray("ingredients").getJSONObject(j).getString("name")
                    val quantity = obj.getJSONObject(i).getJSONArray("ingredients").getJSONObject(j).getString("quantity")
                    ingredients.add(Ingredient(name, quantity))
                }

                for (j in 0 until obj.getJSONObject(i).getJSONArray("instructions").length()) {
                    val content = obj.getJSONObject(i).getJSONArray("instructions").getJSONObject(j).getString("content")
                    instructions.add(content)
                }

                recipe[Recipe(id, categoryId, title, image, servings, instructions)] = ingredients

                // 表示
                Log.d("json", recipe.toString())
            }

            return@withContext recipe
        }
    }

    suspend fun fetchRecipes(
        conditions: String
//        category_id: Int?,
//        title: String?,
//        description: String?,
//        servings: Int?,
//        ingredients: List<Int>?,
//        allergens: List<Int>?,
//        tags: List<Int>?
    ): Result<List<RecipeWithCategory>, String> {
        if (conditions.isNullOrBlank()) {
            return Err("条件が指定されていません")
        }
        //Json格納
        var httpResult = ""

        withContext(Dispatchers.IO) {
            var url = "${baseUrl}/recipe/?${conditions}"

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
            httpResult = br.readText()

            val jsonObj = JSONObject(httpResult)

            val obj =jsonObj.getJSONArray("data")

            //レシピデータをListに格納
            var recipes = emptyArray<RecipeWithCategory>()
            for(i in 0 until obj.length()) {
                val id = obj.getJSONObject(i).getString("id").toInt()
                val categoryId = obj.getJSONObject(i).getString("category_id").toInt()
                val title = obj.getJSONObject(i).getString("title")
                val image = obj.getJSONObject(i).getString("image")

                recipes += RecipeWithCategory(id, categoryId, title, image)
            }

            return@withContext Ok(recipes)
        }
        return Err("Error")
    }
}




