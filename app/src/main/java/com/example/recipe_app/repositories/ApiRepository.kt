package com.example.recipe_app.repositories

import android.app.Application
import android.util.Log
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.data.Recipe
import com.example.recipe_app.data.RecipeWithCategoryId
import com.example.recipe_app.room.favorite_menu.FavoriteMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class ApiRepository @Inject constructor() {

    val recipe = ArrayList<Recipe>()
    //URL（サンプル）
    private var url = "https://umayadia-apisample.azurewebsites.net/api/persons"
    //Json格納
    var httpResult = ""

    //レシピIDから検索
    suspend fun searchRecipeId(recipeId: String){
        url += "/$recipeId"
        recipeData(url)
    }

    private suspend fun recipeData(url: String) {
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
//
//                val id = obj.getJSONObject(i).getString("id").toInt()
//                val categoryId = obj.getJSONObject(i).getString("categoryId").toInt()
//                val title = obj.getJSONObject(i).getString("title")
//                val image = obj.getJSONObject(i).getString("image")
//                val serving = obj.getJSONObject(i).getString("serving").toInt()
//
//                val ingredients =  mutableListOf<Ingredient>()
//                val instructions = mutableListOf<Instruction>()

//                instructions.add(obj.getJSONObject(i).getJSONArray("ingredient").)

//                recipe.add(Recipe(id, categoryId, title, image, serving, ingredients, instructions))

                val name = obj.getJSONObject(i).getString("name")
                // 表示
                Log.d("json", name.toString())
            }

            return@withContext recipe
        }
    }
}




