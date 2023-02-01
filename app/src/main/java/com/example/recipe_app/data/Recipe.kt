package com.example.recipe_app.data

/**
* For Favorite Recipe lists roomに追加
*//*

//data class RecipeWithCategoryId(
//    val id: Int = 0,
//    val categoryId: Int = 0,
//    val title: String = "",
//    val thumb: String = "",
//)

*/
/**
 * For Recipe Details roomに追加
 *//*

//data class Recipe(
//    val id: Int = 0,
//    val categoryId: Int = 0,
//    val title: String = "",
//    val image: String = "",
//    val servings: Int = 0,
//    val ingredients:List<Ingredient> = emptyList(),
//    val instructions: List<Instruction> = emptyList()
//)

*/
/**
 * RecipeDetail property
 *//*

//data class Ingredient(
//    val name: String = "",
//    val quantity: String = ""
//)

*/
/**
 * RecipeDetail property　
 *//*

data class Instruction(
    val order: Int = 0,
    val content: String = ""
)

*/
/**
 * For Favorite Lists
**//*

//data class Favorites(
//    val menuWithoutIngredients: List<MenuWithoutIngredients> = emptyList(),
//    val recipeWithCategoryIds: List<RecipeWithCategoryId> = emptyList()
//)

*/
/**
 * For Menu Lists　roomに追加
 *//*

data class MenuWithoutIngredients(
    val id: Int = 0,
    val recipes: List<RecipeThumb> = emptyList()
)

*/
/**
 * Menu property
 *//*

data class RecipeThumb(
    val id: Int = 0,
    val thumb: String = ""
)

*/
/**
 * For Shopping List roomに追加
 *//*

data class MenuWithRecipeThumbs(
    val id: Int = 0,
    val recipes: List<RecipeThumb> = emptyList(),
    val shoppingItems: List<ShoppingItem> = emptyList()
)

*/
/**
 * MenuDetail property
 *//*

//data class ShoppingItem(
//    val ingredient: Ingredient = Ingredient(),
//    val servings: Int = 0,
//    val isChecked: Boolean = false
//)

//data class Allergen(
//    val id: Int = 0,
//    val name: String = "",
//    val isChecked: Boolean = false
//)*/
