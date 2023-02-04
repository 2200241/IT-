package com.example.recipe_app.data

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "allergens")
data class Allergen(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    @ColumnInfo(name = "is_checked") val isChecked: Boolean = false
)

// For api
data class RecipeBase(
    val id: Int = 0,
    val categoryId: Int = 0,
    val title: String = "",
    val image: String = "",
//    val thumb: String = "",
    val servings: Int = 0,
    val ingredients: List<Ingredient> = emptyList(),
    val instructions: List<String> = emptyList()
)

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "category_id") val categoryId: Int = 0,
    val title: String = "",
    val image: String = "",
//    val thumb: String = "",
    val servings: Int = 0,
//    val instructions: List<String> = emptyList()
)

@Entity(
    tableName = "instructions",
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipe_id")
        )
    ]
)
data class Instruction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "recipe_id", index = true) val recipeId: Int = 0,
    val order: Int = 0,
    val content: String = ""
)

data class DetailedRecipe(
    @Embedded val recipe: Recipe = Recipe(),
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val ingredients: List<RecipeIngredient> = emptyList(),
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val instructions: List<Instruction> = emptyList()
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
    @ColumnInfo(name = "recipe_id", index = true) val recipeId: Int = 0,
    val name: String = "",
    val quantity: String = ""
)

data class Ingredient(
    val name: String = "",
    val quantity: String = ""
)

@Entity(tableName = "menus")
data class Menu(
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

@Entity(
    tableName = "menu_recipes",
    foreignKeys = [
        ForeignKey(
            entity = Menu::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("menu_id")
        ),
        ForeignKey(
            entity = Recipe::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipe_id")
        )
    ]
)
data class MenuRecipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "menu_id", index = true) val menuId: Int = 0,
    @ColumnInfo(name = "recipe_id", index = true) val recipeId: Int = 0
)

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
            childColumns = arrayOf("recipe_ingredient_id")
        )
    ]
)
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "menu_id", index = true) val menuId: Int = 0,
    @ColumnInfo(name = "recipe_ingredient_id", index = true) val recipeIngredientId: Int = 0,
//    val servings: Int = 0,
    @ColumnInfo(name = "is_checked") val isChecked: Boolean = false
)

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
    @ColumnInfo(name = "recipe_id", index = true) val recipeId: Int = 0
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
    @ColumnInfo(name = "menu_id", index = true) val menuId: Int = 0
)