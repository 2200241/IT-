package com.example.recipe_app.repositories

import android.app.Application
import com.example.recipe_app.room.allergy.Allergy
import com.example.recipe_app.room.database.RecipeAppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllergyRepository @Inject constructor(application: Application){

    private val allergyDao = RecipeAppDatabase.getDatabase(application).allergyDao()

    //全件取得
    fun getAllAllergies() = allergyDao.getAllAllergies()

    //追加
    suspend fun addAllergy(allergy: Allergy) {
        withContext(Dispatchers.IO){
            allergyDao.addAllergy(allergy)
        }
    }

    //追加
    suspend fun checkAllergy(id: Int, check: Boolean) {
        withContext(Dispatchers.IO){
            allergyDao.checkAllergy(id, check)
        }
    }

    //指定したIDを削除
    suspend fun deleteAllergy(allergy_id: Int){
        withContext(Dispatchers.IO) {
            allergyDao.deleteAllergy(allergy_id)
        }
    }

    //更新
    suspend fun updateAllergy(allergy: Allergy){
        withContext(Dispatchers.IO) {
            allergyDao.updateAllergy(allergy)
        }
    }

    //全件削除
    suspend fun deleteAllAllergies(){
        withContext(Dispatchers.IO) {
            allergyDao.deleteAllAllergies()
        }
    }
}
