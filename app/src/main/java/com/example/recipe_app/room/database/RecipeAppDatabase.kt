package com.example.recipe_app.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe_app.room.allergy.Allergy
import com.example.recipe_app.room.allergy.AllergyDao
import com.example.recipe_app.room.favorite.Favorite
import com.example.recipe_app.room.favorite.FavoriteDao
//import com.example.recipe_app.room.menu.Menu
//import com.example.recipe_app.room.menu.MenuDao
//import com.example.recipe_app.room.menu.Recipe
//import com.example.recipe_app.room.menu.RecipeDao

@Database(entities = [Favorite::class, Allergy::class,], version = 1, exportSchema = false)
abstract class RecipeAppDatabase: RoomDatabase() {
//    Menu::class, Recipe::class
    abstract fun favoriteDao(): FavoriteDao
    abstract fun allergyDao(): AllergyDao
//    abstract fun menuDao(): MenuDao
//    abstract fun recipeDao(): RecipeDao

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