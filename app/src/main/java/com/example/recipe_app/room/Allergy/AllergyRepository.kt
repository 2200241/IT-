package com.example.recipe_app.room.Allergy

import com.example.recipe_app.room.Allergy.Allergy
import com.example.recipe_app.room.Allergy.AllergyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AllergyRepository(private val AllergyDao : AllergyDao) {

    //全件取得
    fun getAllData() = AllergyDao.readAllData()

    //追加
    suspend fun addFavorite(allergy: Allergy) {
        withContext(Dispatchers.IO){
            AllergyDao.addAllergy(allergy)
        }
    }

    //指定したIDを削除
    suspend fun deleteFavorite(allergy_id: Int){
        withContext(Dispatchers.IO) {
            AllergyDao.deleteAllergy(allergy_id)
        }
    }

    //更新
    suspend fun updateFavorite(allergy: Allergy){
        withContext(Dispatchers.IO) {
            AllergyDao.updateAllergy(allergy)
        }
    }

    //全件削除
    suspend fun deleteAllFavorites(){
        withContext(Dispatchers.IO) {
            AllergyDao.deleteAllAllergies()
        }
    }
}
