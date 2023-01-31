package com.example.recipe_app.use_cases.allergy

//import com.example.recipe_app.data.*
//import com.example.recipe_app.repositories.AllergyRepository
//import com.example.recipe_app.repositories.FavoriteMenuRepository
//import com.example.recipe_app.repositories.FavoriteRecipeRepository
//import com.example.recipe_app.room.allergy.Allergy
//import com.example.recipe_app.room.favorite_menu.FavoriteMenu
//import com.example.recipe_app.room.favorite_recipe.FavoriteRecipe
//import com.example.recipe_app.room.shoppinglist.ShoppingList
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class GetAllergyUseCase @Inject constructor(private val allergyRepository: AllergyRepository){
//
//    var allergyList: Flow<List<Allergy>> = allergyRepository.getAllAllergies()
//
//    @Throws(InvalidGetFavoritesException::class)
//    suspend fun getAllergy(): Flow<List<Allergy>> {
//
//        allergyList.collect {
//            if (it.isEmpty()) {
//                throw InvalidGetFavoritesException("お気に入りがありません")
//            }
//        }
//        return allergyList
//    }
//
//    suspend fun setTestAllergyData(): Flow<List<Allergy>> {
//        val allergy = Allergy(0, "たまご", false)
//        allergyRepository.addAllergy(allergy)
//        return allergyRepository.getAllAllergies()
//    }
//
//}
//
//class InvalidGetFavoritesException(message: String): Exception(message)