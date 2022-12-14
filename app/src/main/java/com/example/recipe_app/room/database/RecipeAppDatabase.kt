package com.example.recipe_app.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipe_app.room.allergy.Allergy
import com.example.recipe_app.room.allergy.AllergyDao
import com.example.recipe_app.room.favorite.Favorite
import com.example.recipe_app.room.cache.Cache
import com.example.recipe_app.room.cache.CacheDao
import com.example.recipe_app.room.favorite.FavoriteDao
import com.example.recipe_app.room.menu.Menu
import com.example.recipe_app.room.menu.MenuDao
import com.example.recipe_app.room.menu.MenusTypeConverter
import com.example.recipe_app.room.recipe.Recipe
import com.example.recipe_app.room.recipe.RecipeDao
import com.example.recipe_app.room.recipe.RecipesTypeConverter
import com.example.recipe_app.room.shoppingitem.ShoppingItem
import com.example.recipe_app.room.shoppingitem.ShoppingItemDao
import com.example.recipe_app.room.shoppingitem.ShoppingItemTypeConverter

@Database(entities = [Favorite::class, Allergy::class, Recipe::class, Menu::class, ShoppingItem::class, Cache::class], version = 2, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class, MenusTypeConverter::class, ShoppingItemTypeConverter::class)

abstract class RecipeAppDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
    abstract fun allergyDao(): AllergyDao
    abstract fun recipeDao(): RecipeDao
    abstract fun menuDao(): MenuDao
    abstract fun shoppingItemDao(): ShoppingItemDao
    abstract fun cacheDao(): CacheDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeAppDatabase? = null

        fun getDatabase(
            context: Context
        ): RecipeAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeAppDatabase::class.java,
                    "recipe_app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}