package com.example.recipe_app.data

data class Recipe(
    val id: String = "",
    val categoryId: String = "",
    val title: String = "",
    val thumb: String = "",
    val isFavorite: Boolean = false
)

data class Favorites(
    val menus: List<Menu> = emptyList(),
    val recipes: List<Recipe> = emptyList()
)

data class Menu(
    val id: String = "",
    val date: String = "",
    val recipes: List<Recipe> = emptyList()
)

// 表示するときにmapでfilterすればいい
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
