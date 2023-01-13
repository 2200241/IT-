package com.example.recipe_app.repositories

import android.app.Application
import android.util.Log
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.data.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class ApiRepository @Inject constructor(application: Application) {


    val recipe = ArrayList<Recipe>()
    //サンプルURL
    private val url = "https://umayadia-apisample.azurewebsites.net/api/persons"
    //Json格納
    var httpResult = ""

    suspend fun recipeAllData() {
        withContext(Dispatchers.IO) {
            val urlObj = URL(url)

            // アクセスしたAPIから情報を取得
            //テキストファイルを読み込むクラス(文字コードを読めるようにする準備(URLオブジェクト))
            val br = BufferedReader(InputStreamReader(urlObj.openStream()))

            //読み込んだデータを文字列に変換して代入
            httpResult = br.readText()

            val jsonObj = JSONObject(httpResult)

            val obj =jsonObj.getJSONArray("data")

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
        }
    }
}




