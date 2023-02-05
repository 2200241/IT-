package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

/*
    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image FROM menus " +
            "JOIN recipes ON recipes.id IN (SELECT menu_recipes.recipe_id FROM menu_recipes WHERE menu_recipes.menu_id = menus.id) " +
            "ORDER BY menus.id"
    )
*/
    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image FROM menus " +
            "JOIN menu_recipes ON menu_recipes.menu_id = menus.id " +
            "JOIN recipes ON recipes.id = menu_recipes.recipe_id " +
            "ORDER BY menus.id"
    )
    fun getAllMenus(): Flow<Map<Menu, List<RecipeWithoutCategory>>>

/*
    @Query("SELECT recipes.id, recipes.title, recipes.image, " +
            "shopping_items.id, shopping_items.is_checked, recipe_ingredients.name, recipe_ingredients.quantity, recipes.servings " +
            "FROM menus " +
            "JOIN recipes ON recipes.id IN (SELECT menu_recipes.recipe_id FROM menu_recipes WHERE menu_recipes.menu_id = menus.id) " +
            "JOIN shopping_items ON shopping_items.menu_id = menus.id " +
            "JOIN recipe_ingredients ON recipe_ingredients.id = shopping_items.recipe_ingredient_id " +
            "WHERE menus.id = :id"
    )
*/
/*    @Query("SELECT recipes.id, recipes.title, recipes.image, " +
            "shopping_items.id, shopping_items.is_checked, recipe_ingredients.name, recipe_ingredients.quantity, recipes.servings " +
            "FROM menus " +
            "JOIN menu_recipes ON menu_recipes.menu_id = menus.id " +
            "JOIN recipes ON recipes.id = menu_recipes.recipe_id " +
            "JOIN shopping_items ON shopping_items.menu_id = menus.id " +
            "JOIN recipe_ingredients ON recipe_ingredients.id = shopping_items.recipe_ingredient_id " +
            "WHERE menus.id = :id"
    )
    fun getMenuDetail(id: Int): Flow<Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>>*/

    @Query("SELECT recipes.id, recipes.title, recipes.image " +
            "FROM menu_recipes " +
            "JOIN recipes ON recipes.id = menu_recipes.recipe_id " +
            "WHERE menu_recipes.menu_id = :id"
    )
    fun getMenuRecipes(id: Int): Flow<List<RecipeWithoutCategory>>

    @Query("SELECT DISTINCT shopping_items.id, shopping_items.is_checked, recipe_ingredients.name, recipe_ingredients.quantity, recipes.servings " +
            "FROM shopping_items " +
            "JOIN menu_recipes ON menu_recipes.menu_id = shopping_items.menu_id " +
            "JOIN recipes ON recipes.id = menu_recipes.recipe_id " +
            "JOIN recipe_ingredients ON recipe_ingredients.id = shopping_items.recipe_ingredient_id " +
            "WHERE menu_recipes.menu_id = :id GROUP BY shopping_items.id ORDER BY recipe_ingredients.name"
    )
    fun getShoppingItems(id: Int): Flow<List<ShoppingItemWithIngredient>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMenu(menu: Menu): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipes(
        recipes: List<Recipe>,
        instructions: List<Instruction>
    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredients(ingredients: List<RecipeIngredient>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMenuRecipes(menuRecipes: List<MenuRecipe>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShoppingItems(shoppingItems: List<ShoppingItem>)

    @Query("DELETE FROM menus WHERE id = :id")
    suspend fun deleteMenu(id: Int)

    @Query("DELETE FROM shopping_items WHERE menu_id = :id")
    suspend fun deleteShoppingItems(id: Int)

    @Query("DELETE FROM menu_recipes WHERE menu_id = :id")
    suspend fun deleteMenuRecipes(id: Int)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Query("DELETE FROM menus")
    suspend fun deleteAllMenus()

//    @Query("UPDATE shopping_items SET is_checked = IIF(is_checked = 1, 0, 1) WHERE id = :id")
    @Query("UPDATE shopping_items SET is_checked = CASE WHEN is_checked = 1 THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun checkShoppingItem(id: Int)
}