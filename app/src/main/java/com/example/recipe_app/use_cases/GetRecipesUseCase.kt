package com.example.recipe_app.use_cases

import android.util.Log
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.repositories.RecipeRepository
import com.example.recipe_app.room.recipe.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository){

    @Throws(InvalidGetRecipesException::class)
    suspend fun getRecipe(): Flow<List<Recipe>> {

        var recipeList: Flow<List<Recipe>> = recipeRepository.getAllRecipes()

        recipeList.collect(){
            if (it.isEmpty()) {
                throw InvalidGetRecipesException("recipeList is empty")
            }
            if (it.first().id.toString().isBlank()) {
                throw InvalidGetRecipesException("お気に入りがありません")
            }
        }

        return recipeList
    }

    // サンプルデータ
    suspend fun setTestRecipeData(): Flow<List<Recipe>> {
        recipeRepository.deleteAllRecipes()
        val ingredient = ArrayList<Ingredient>()
        ingredient.add(Ingredient("name", "quantity"))
        val instruction = ArrayList<Instruction>()
        instruction.add(Instruction(1 , "content"))
        val recipe = Recipe(
            1,
            1,
            "title",
            "image",
            "thumb",
            1,
            ingredient,
            instruction
        )
        recipeRepository.addRecipe(recipe)

        return recipeRepository.getAllRecipes()
    }
}

class InvalidGetRecipesException(message: String): Exception(message)

