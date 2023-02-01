package com.example.recipe_app.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.recipe_app.data.*
import com.example.recipe_app.data.dao.*

@Database(entities = [
    Allergen::class,
    Recipe::class,
    Instruction::class,
    RecipeIngredient::class,
    Menu::class,
    MenuRecipe::class,
    FavoriteRecipeId::class,
    FavoriteMenuId::class,
    ShoppingItem::class
], version = 1, exportSchema = false)
//@TypeConverters(RecipesTypeConverter::class, FavoriteMenusTypeConverter::class)

abstract class RecipeAppDatabase: RoomDatabase() {

    abstract fun allergenDao(): AllergenDao
    abstract fun recipeDetailDao(): RecipeDetailDao
    abstract fun menuDao(): MenuDao
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
    abstract fun favoriteMenuDao(): FavoriteMenuDao

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
                    .addCallback(object: RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val sql = "INSERT INTO 'allergens' VALUES " +
                                    "(1, 'えび', 0)," +
                                    "(2, 'かに', 0)," +
                                    "(3, '小麦', 0)," +
                                    "(4, 'そば', 0)," +
                                    "(5, '卵', 0)," +
                                    "(6, '乳', 0)," +
                                    "(7, '落花生', 0)," +
                                    "(101, 'アーモンド', 0)," +
                                    "(102, 'あわび', 0)," +
                                    "(103, 'いか', 0)," +
                                    "(104, 'いくら', 0)," +
                                    "(105, 'オレンジ', 0)," +
                                    "(106, 'カシューナッツ', 0)," +
                                    "(107, 'キウイフルーツ', 0)," +
                                    "(108, '牛肉', 0)," +
                                    "(109, 'くるみ', 0)," +
                                    "(110, 'ごま', 0)," +
                                    "(111, 'さけ', 0)," +
                                    "(112, 'さば', 0)," +
                                    "(113, '大豆', 0)," +
                                    "(114, '鶏肉', 0)," +
                                    "(115, 'バナナ', 0)," +
                                    "(116, '豚肉', 0)," +
                                    "(117, 'まつたけ', 0)," +
                                    "(118, 'もも', 0)," +
                                    "(119, 'やまいも', 0)," +
                                    "(120, 'りんご', 0)," +
                                    "(121, 'ゼラチン', 0)";
                            db.execSQL(sql)
                        }
                    })
//                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}