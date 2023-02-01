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
                                    "(1, 'えび')," +
                                    "(2, 'かに')," +
                                    "(3, '小麦')," +
                                    "(4, 'そば')," +
                                    "(5, '卵')," +
                                    "(6, '乳')," +
                                    "(7, '落花生')," +
                                    "(101, 'アーモンド')," +
                                    "(102, 'あわび')," +
                                    "(103, 'いか')," +
                                    "(104, 'いくら')," +
                                    "(105, 'オレンジ')," +
                                    "(106, 'カシューナッツ')," +
                                    "(107, 'キウイフルーツ')," +
                                    "(108, '牛肉')," +
                                    "(109, 'くるみ')," +
                                    "(110, 'ごま')," +
                                    "(111, 'さけ')," +
                                    "(112, 'さば')," +
                                    "(113, '大豆')," +
                                    "(114, '鶏肉')," +
                                    "(115, 'バナナ')," +
                                    "(116, '豚肉')," +
                                    "(117, 'まつたけ')," +
                                    "(118, 'もも')," +
                                    "(119, 'やまいも')," +
                                    "(120, 'りんご')," +
                                    "(121, 'ゼラチン')";
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