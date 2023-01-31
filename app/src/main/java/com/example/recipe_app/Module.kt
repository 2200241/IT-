package com.example.recipe_app

import com.example.recipe_app.repositories.AllergenRepository
import com.example.recipe_app.repositories.ApiRepository
import com.example.recipe_app.repositories.FavoriteMenuRepository
import com.example.recipe_app.repositories.FavoriteRecipeRepository
import com.example.recipe_app.use_cases.allergy.AllergyUseCases
import com.example.recipe_app.use_cases.favorite_menu.FavoriteMenuUseCases
import com.example.recipe_app.use_cases.favorite_recipe.FavoriteRecipeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideApiRepository(): ApiRepository = ApiRepository()
// = ApiRepositoryImpl()

    @Provides
    fun allergyRepository(application: RecipeApplication): AllergenRepository = AllergenRepository(application)

    @Provides
    fun allergyUseCases(allergenRepository: AllergenRepository): AllergyUseCases = AllergyUseCases(allergenRepository)

    @Provides
    fun favoriteRecipeRepository(application: RecipeApplication): FavoriteRecipeRepository = FavoriteRecipeRepository(application)

    @Provides
    fun favoriteRecipeUseCases(favoriteRecipeRepository: FavoriteRecipeRepository): FavoriteRecipeUseCases = FavoriteRecipeUseCases(favoriteRecipeRepository)

    @Provides
    fun favoriteMenuRepository(application: RecipeApplication): FavoriteMenuRepository = FavoriteMenuRepository(application)

    @Provides
    fun favoriteMenuUseCases(favoriteMenuRepository: FavoriteMenuRepository): FavoriteMenuUseCases = FavoriteMenuUseCases(favoriteMenuRepository)
}