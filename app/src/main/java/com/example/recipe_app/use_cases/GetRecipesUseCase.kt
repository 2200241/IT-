package com.example.recipe_app.use_cases

import android.util.Log
import com.example.recipe_app.data.Ingredient
import com.example.recipe_app.data.Instruction
import com.example.recipe_app.repositories.RecipeRepository
import com.example.recipe_app.room.recipe.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val recipeRepository: RecipeRepository){

    private var recipeList: List<Recipe> = emptyList()

    @Throws(InvalidGetRecipesException::class)
    suspend fun getRecipe(): List<Recipe> {

        recipeList = recipeRepository.getAllRecipes()

        if (recipeList.isEmpty()) {
            throw InvalidGetRecipesException("recipeList is empty")
        }
        if (recipeList.first().id.toString().isBlank()) {
            throw InvalidGetRecipesException("お気に入りがありません")
        }

        return recipeList
    }

    // サンプルデータ
    suspend fun setTestRecipeData(): List<Recipe> {
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

