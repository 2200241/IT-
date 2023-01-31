package com.example.recipe_app.use_cases

//import com.example.recipe_app.data.*
//import com.example.recipe_app.repositories.TestRepository
//import com.github.michaelbull.result.Result
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//interface TestUseCase {
//    suspend fun fetchRecipes(conditions: String): Result<List<RecipeWithCategory>, String>
//    suspend fun fetchRecipeDetail(id: Int): Result<Recipe, String>
////    fun fetchFavorites(): Flow<Result<Favorites, String>>
//    suspend fun addFavoriteRecipe(recipeWithCategory: RecipeWithCategory): Result<String, String>
//    suspend fun removeFavoriteRecipe(id: Int): Result<String, String>
//    suspend fun addFavoriteMenu(menuWithoutIngredients: MenuWithoutIngredients): Result<String, String>
//    suspend fun removeFavoriteMenu(id: Int): Result<String, String>
//    fun fetchMenus(): Flow<Result<List<MenuWithoutIngredients>, String>>
//    fun fetchMenu(id: Int): Flow<Result<MenuWithRecipeThumbs, String>>
//    suspend fun checkShoppingListItem(id: Int, index: Int): Result<String, String>
//    suspend fun addMenu(): Result<String, String>
//    suspend fun removeMenu(id: Int): Result<String, String>
//    fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>>
//    suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String>
//    suspend fun removeFromTempMenu(id: Int): Result<String, String>
//    suspend fun removeAllFromTempMenu(): Result<String, String>
//}
//
//class TestUseCaseImpl @Inject constructor(
//    private val testRepository: TestRepository
//): TestUseCase {
//
//    override suspend fun fetchRecipes(conditions: String): Result<List<RecipeWithCategory>, String> {
//        return testRepository.fetchRecipes(conditions)
//    }
//
//    override suspend fun fetchRecipeDetail(id: Int): Result<Recipe, String> {
//        return testRepository.fetchRecipeDetail(id)
//    }
//
////    override fun fetchFavorites(): Flow<Result<Favorites, String>> {
////        return testRepository.fetchFavorites()
////    }
//
//    override suspend fun addFavoriteRecipe(recipeWithCategory: RecipeWithCategory): Result<String, String> {
//        return testRepository.addFavoriteRecipe(recipeWithCategory)
//    }
//
//    override suspend fun removeFavoriteRecipe(id: Int): Result<String, String> {
//        return testRepository.removeFavoriteRecipe(id)
//    }
//
//    override suspend fun addFavoriteMenu(menuWithoutIngredients: MenuWithoutIngredients): Result<String, String> {
//        return testRepository.addFavoriteMenu(menuWithoutIngredients)
//    }
//
//    override suspend fun removeFavoriteMenu(id: Int): Result<String, String> {
//        return testRepository.removeFavoriteMenu(id)
//    }
//
//    override fun fetchMenus(): Flow<Result<List<MenuWithoutIngredients>, String>> {
//        return testRepository.fetchMenus()
//    }
//
//    override fun fetchMenu(id: Int): Flow<Result<MenuWithRecipeThumbs, String>> {
//        return testRepository.fetchMenu(id)
//    }
//
//    override suspend fun checkShoppingListItem(id: Int, index: Int): Result<String, String> {
//        return testRepository.checkShoppingListItem(id, index)
//    }
//
//    override suspend fun addMenu(): Result<String, String> {
//        return testRepository.addMenu()
//    }
//
//    override suspend fun removeMenu(id: Int): Result<String, String> {
//        return testRepository.removeMenu(id)
//    }
//
//    override fun getTempMenu(): Flow<Result<List<RecipeThumb>, String>> {
//        return testRepository.getTempMenu()
//    }
//
//    override suspend fun addToTempMenu(recipe: RecipeThumb): Result<String, String> {
//        return testRepository.addToTempMenu(recipe)
//    }
//
//    override suspend fun removeFromTempMenu(id: Int): Result<String, String> {
//        return testRepository.removeFromTempMenu(id)
//    }
//
//    override suspend fun removeAllFromTempMenu(): Result<String, String> {
//        return testRepository.removeAllFromTempMenu()
//    }
//}