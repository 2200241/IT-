package com.example.recipe_app.data

/**
* For lists
*/
data class RecipeWithCategoryId(
    val id: Int = 0,
    val categoryId: Int = 0,
    val title: String = "",
    val thumb: String = "",
)

/**
 * For Recipe Details
 */
data class Recipe(
    val id: Int = 0,
    val categoryId: Int = 0,
    val title: String = "",
    val image: String = "",
    val serving: Int = 0,
    val ingredients:List<Ingredient> = emptyList(),
    val instructions: List<Instruction> = emptyList()
)

/**
 * RecipeDetail property
 */
data class Ingredient(
    val name: String = "",
    val quantity: String = ""
)

/**
 * RecipeDetail property
 */
data class Instruction(
    val order: Int = 0,
    val content: String = ""
)

/**
 * For Favorite Lists
**/
data class Favorites(
    val menuWithoutIngredients: List<MenuWithoutIngredients> = emptyList(),
    val recipeWithCategoryIds: List<RecipeWithCategoryId> = emptyList()
)

/**
 * For Menu Lists
 */
data class MenuWithoutIngredients(
    val id: Int = 0,
    val recipes: List<RecipeThumb> = emptyList()
)

/**
 * Menu property
 */
data class RecipeThumb(
    val id: Int = 0,
    val thumb: String = ""
)

/**
 * For Shopping List
 */
data class MenuWithRecipeThumbs(
    val id: Int = 0,
    val recipes: List<RecipeThumb> = emptyList(),
    val shoppingItems: List<ShoppingItem> = emptyList()
)

/**
 * MenuDetail property
 */
data class ShoppingItem(
    val ingredient: Ingredient = Ingredient(),
    val serving: Int = 0,
    val isChecked: Boolean = false
)