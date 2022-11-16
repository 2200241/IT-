package com.example.recipe_app.room.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipe_app.room.Allergy.Allergy
import com.example.recipe_app.room.Allergy.AllergyDao
import com.example.recipe_app.room.Favorite.Favorite
import com.example.recipe_app.room.Favorite.FavoriteDao

@Database(entities = arrayOf(Favorite::class, Allergy::class), version = 1, exportSchema = false)
abstract class RecipeAppDatabase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
    abstract fun AllergyDao(): AllergyDao

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