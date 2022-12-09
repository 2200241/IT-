package com.example.recipe_app.data

//
/**
* For lists
*/
data class Recipe(
    val id: String = "",
    val categoryId: Int = 0,
    val title: String = "",
    val thumb: String = "",
//    val isFavorite: Boolean = false
)

/**
 * For Recipe Details
 */
data class RecipeDetail(
    val id: String = "",
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
    val menus: List<Menu> = emptyList(),
    val recipes: List<Recipe> = emptyList()
)

/**
 * For Menu Lists
 */
data class Menu(
    val id: String = "",
    val recipes: List<RecipeThumb> = emptyList()
)

/**
 * Menu property
 */
data class RecipeThumb(
    val id: String = "",
    val thumb: String = ""
)

/**
// * For Shopping List
// */
data class MenuDetail(
    val menu: Menu = Menu(),
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