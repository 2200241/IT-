package com.example.recipe_app.data

data class Recipe(
    val id: String = "",
    val categoryId: Int = 0,
    val title: String = "",
    val thumb: String = "",
//    val isFavorite: Boolean = false
)

data class RecipeDetail(
    val id: String = "",
    val categoryId: Int = 0,
    val title: String = "",
    val image: String = "",
    val ingredients:List<Ingredient> = emptyList(),
    val instructions: List<Instruction> = emptyList()
)

data class Ingredient(
    val name: String = "",
    val quantity: String = ""
)

data class Instruction(
    val order: Int = 0,
    val content: String = ""
)

data class Favorites(
    val menus: List<Menu> = emptyList(),
    val recipes: List<Recipe> = emptyList()
)

data class Menu(
    val id: String = "",
    val date: String = "",
    val recipes: List<RecipeThumb> = emptyList()
)

// For Menu property
data class RecipeThumb(
    val id: String = "",
    val thumb: String = ""
)

// 表示するときにfilterすればいい
/*
data class Categories(
    val stapleFood: List<Recipe> = emptyList(),
    val mainDish: List<Recipe> = emptyList(),
    val sideDish: List<Recipe> = emptyList(),
    val soup: List<Recipe> = emptyList(),
    val sweets: List<Recipe> = emptyList(),
    val drink: List<Recipe> = emptyList(),
    val others: List<Recipe> = emptyList()
)*/
