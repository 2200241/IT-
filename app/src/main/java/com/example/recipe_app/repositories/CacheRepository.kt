package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.cache.Cache
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheRepository @Inject constructor(application: Application){

    private val cacheDao = RecipeAppDatabase.getDatabase(application).cacheDao()

    //全件取得
    suspend fun getAllCaches(): List<Cache> = cacheDao.getAllCaches()

    //追加
    suspend fun addCache(cache: Cache) {
        withContext(Dispatchers.IO){
            cacheDao.addCache(cache)
        }
    }

    //全件削除
    suspend fun deleteAllCaches(){
        withContext(Dispatchers.IO) {
            cacheDao.deleteAllCaches()
        }
    }
}
