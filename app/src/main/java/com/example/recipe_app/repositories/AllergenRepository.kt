package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.data.Allergen
import com.example.recipe_app.room.database.RecipeAppDatabase
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AllergenRepository {
    fun getAllAllergens(): Flow<List<Allergen>>
    fun getCheckedAllergens(): Flow<List<Int>>
    suspend fun checkAllergen(id: Int, isChecked: Boolean): Result<String, String>
}

class AllergenRepositoryImpl @Inject constructor(application: Application):AllergenRepository  {

    private val allergenDao = RecipeAppDatabase.getDatabase(application).allergenDao()

    //全件取得
    override fun getAllAllergens() = allergenDao.getAllAllergens()

    override fun getCheckedAllergens() = allergenDao.getCheckedAllergens()

    //追加
//    suspend fun addAllergen(allergen: Allergen) {
//        withContext(Dispatchers.IO){
//            allergenDao.addAllergen(allergen)
//        }
//    }

    override suspend fun checkAllergen(id: Int, isChecked: Boolean): Result<String, String> {
        try {
            return withContext(Dispatchers.IO) {
                allergenDao.checkAllergen(id, isChecked)
                return@withContext Ok("Success")
            }
        } catch (e: Exception) {
            return Err(e.toString())
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
