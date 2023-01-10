package com.example.recipe_app

import android.app.Application
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipeApplication(): Application() {
//    override fun onCreate() {
//        super.onCreate()
//
//        // TODO: アプリ生成時処理
//    }
//
//    override fun onTerminate() {
//        super.onTerminate()
//        // TODO: アプリ終了時処理
//
//    }
}