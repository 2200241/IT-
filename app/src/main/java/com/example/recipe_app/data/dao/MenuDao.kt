package com.example.recipe_app.data.dao

import androidx.room.*
import com.example.recipe_app.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
//    data class ShoppingItemWithIngredient(
//        val id: Int = 0,
//        val name: String = "",
//        val quantity: String = "",
//        val servings: Int = 0,
//        @ColumnInfo(name = "is_checked") val isChecked: Boolean = false
//    )
    @Query("SELECT menus.id, recipes.id, recipes.title, recipes.image " +
            "FROM menus JOIN recipes ON recipes.id IN (menus.recipe_ids) "
    )
    fun getAllMenus(): Flow<Map<Int, List<RecipeWithoutCategory>>>

    @Query("SELECT recipes.id, recipes.title, recipes.image, " +
            "shopping_items.id, shopping_items.is_checked, recipe_ingredients.name, recipe_ingredients.quantity, recipes.servings " +
            "FROM menus " +
            "JOIN recipes ON recipes.id IN (menus.recipe_ids) " +
            "JOIN shopping_items ON shopping_items.menu_id = menus.id " +
            "JOIN recipe_ingredients ON recipe_ingredients.id = shopping_items.recipe_ingredient_id " +
            "WHERE menus.id = :id"
    )
    fun getMenuDetail(id: Int): Flow<Map<RecipeWithoutCategory, List<ShoppingItemWithIngredient>>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMenu(
        menu: Menu,
        recipes: List<Recipe>,
        recipeIngredients: List<RecipeIngredient>
    ): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShoppingItems(shoppingItems: List<ShoppingItem>)

    @Query("delete from menus where id = :id")
    suspend fun deleteMenu(id: Int)

    @Update
    suspend fun updateMenu(menu: Menu)

    @Query("delete from menus")
    suspend fun deleteAllMenus()

    @Query("UPDATE shopping_items SET is_checked = IIF(is_checked = 1, 0, 1)")
    suspend fun checkShoppingItem(id: Int)
}