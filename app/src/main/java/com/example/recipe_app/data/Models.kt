package com.example.recipe_app.data

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "allergens")
data class Allergen(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    @ColumnInfo(name = "is_checked") val isChecked: Boolean = false
)

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "category_id") val categoryId: Int = 0,
    val title: String = "",
    val image: String = "",
//    val thumb: String = "",
    val servings: Int = 0,
    val instructions: List<String> = emptyList()
)

data class RecipeWithCategory(
    val id: Int = 0,
    @ColumnInfo(name = "category_id") val categoryId: Int = 0,
    val title: String = "",
    val image: String = "",
)

data class RecipeWithoutCategory(
    val id: Int = 0,
    val title: String = "",
    val image: String = "",
)

@Entity(
    tableName = "recipe_ingredients",
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("recipe_id")
    )]
)
data class RecipeIngredient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe_id") @NotNull val recipeId: Int = 0,
    val name: String = "",
    val quantity: String = ""
)

data class Ingredient(
    val name: String = "",
    val quantity: String = ""
)

@Entity(
    tableName = "menus",
//    foreignKeys = [ForeignKey(
//        entity = Recipe::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("recipe_ids")
//    )]
)
data class Menu(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe_ids") val recipeIds: List<Int> = emptyList(),
)
// MenuWithRecipes: Map<Int, List<RecipeWithCategory>>

@Entity(
    tableName = "shopping_items",
    foreignKeys = [
        ForeignKey(
            entity = Menu::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("menu_id")
        ),
        ForeignKey(
            entity = RecipeIngredient::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipe_ingredients_id")
        )
    ]
)
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "menu_id")  @NotNull val menuId: Int = 0,
    @ColumnInfo(name = "recipe_ingredient_id") @NotNull val recipeIngredientId: Int = 0,
//    val servings: Int = 0,
    @ColumnInfo(name = "is_checked") val isChecked: Boolean = false
)
// MenuWithShoppingItems: Map<Menu, List<ShoppingItem>>

data class ShoppingItemWithIngredient(
    val id: Int = 0,
    val name: String = "",
    val quantity: String = "",
    val servings: Int = 0,
    @ColumnInfo(name = "is_checked") val isChecked: Boolean = false
)

@Entity(
    tableName = "favorite_recipe_ids",
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipe_id")
        )
    ]
)
data class FavoriteRecipeId(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe_id") @NotNull val recipeId: Int = 0
)

@Entity(
    tableName = "favorite_menu_ids",
    foreignKeys = [
        ForeignKey(
            entity = Menu::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("menu_id")
        )
    ]
)
data class FavoriteMenuId(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "menu_id") @NotNull val menuId: Int = 0
)