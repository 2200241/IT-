package com.example.recipe_app.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipe_app.room.allergy.Allergy
import com.example.recipe_app.room.allergy.AllergyDao
import com.example.recipe_app.room.cache.Cache
import com.example.recipe_app.room.cache.CacheDao
import com.example.recipe_app.room.favorite_menu.*
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
import com.example.recipe_app.room.favorite_recipe.FavoriteRecipeDao
import com.example.recipe_app.room.recipe.*
import com.example.recipe_app.room.shoppingitem.*

@Database(entities = [FavoriteRecipe::class, Allergy::class, Recipe::class, FavoriteMenu::class, MenuWithRecipeThumb::class, Cache::class], version = 2, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class, FavoriteMenusTypeConverter::class, MenuWithRecipeThumbTypeConverter::class)

abstract class RecipeAppDatabase: RoomDatabase() {

    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
    abstract fun allergyDao(): AllergyDao
    abstract fun recipeDao(): RecipeDao
    abstract fun favoriteMenuDao(): FavoriteMenuDao
    abstract fun menuWithRecipeThumbDao(): MenuWithRecipeThumbDao
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