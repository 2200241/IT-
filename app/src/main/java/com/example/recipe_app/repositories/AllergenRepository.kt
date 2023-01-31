package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.Allergen
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AllergenRepository {
    fun getAllAllergens(): Flow<List<Allergen>>
    suspend fun checkAllergen(id: Int, isChecked: Boolean)
}

class AllergenRepositoryImpl @Inject constructor(application: Application):AllergenRepository  {

    private val allergenDao = RecipeAppDatabase.getDatabase(application).allergenDao()

    //全件取得
    override fun getAllAllergens() = allergenDao.getAllAllergens()

    //追加
//    suspend fun addAllergen(allergen: Allergen) {
//        withContext(Dispatchers.IO){
//            allergenDao.addAllergen(allergen)
//        }
//    }

    override suspend fun checkAllergen(id: Int, isChecked: Boolean) {
        withContext(Dispatchers.IO){
            allergenDao.checkAllergen(id, isChecked)
        }
    }

    //指定したIDを削除
//    suspend fun deleteAllergen(id: Int){
//        withContext(Dispatchers.IO) {
//            allergenDao.deleteAllergen(id)
//        }
//    }

    //更新
//    suspend fun updateAllergen(allergen: Allergen){
//        withContext(Dispatchers.IO) {
//            allergenDao.updateAllergen(allergen)
//        }
//    }

    //全件削除
//    suspend fun deleteAllAllergen(){
//        withContext(Dispatchers.IO) {
//            allergenDao.deleteAllAllergens()
//        }
//    }
}
