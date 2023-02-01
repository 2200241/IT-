package com.example.recipe_app

import android.app.Application
import com.example.recipe_app.repositories.*
import com.example.recipe_app.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideApiRepository(): ApiRepository = ApiRepositoryImpl()

    @Provides
    fun provideAllergenRepository(application: Application): AllergenRepository = AllergenRepositoryImpl(application)

    @Provides
    fun provideAllergenUseCase(allergenRepository: AllergenRepository): AllergenUseCase = AllergenUseCaseImpl(allergenRepository)

    @Provides
    fun provideFavoriteRecipeRepository(application: Application): FavoriteRecipeRepository = FavoriteRecipeRepositoryImpl(application)

    @Provides
    fun provideFavoriteRecipeUseCase(
        favoriteRecipeRepository: FavoriteRecipeRepository,
        apiRepository: ApiRepository
    ): FavoriteRecipeUseCase = FavoriteRecipeUseCaseImpl(favoriteRecipeRepository, apiRepository)

    @Provides
    fun provideFavoriteMenuRepository(application: Application): FavoriteMenuRepository = FavoriteMenuRepositoryImpl(application)

    @Provides
    fun provideFavoriteMenuUseCase(favoriteMenuRepository: FavoriteMenuRepository): FavoriteMenuUseCase = FavoriteMenuUseCaseImpl(favoriteMenuRepository)

    @Provides
    fun provideMenuRepository(application: Application): MenuRepository = MenuRepositoryImpl(application)

    @Provides
    fun provideMenuUseCase(menuRepository: MenuRepository): MenuUseCase = MenuUseCaseImpl(menuRepository)

    @Provides
    fun provideRecipeRepository(application: Application): RecipeRepository = RecipeRepositoryImpl(application)

    @Provides
    fun provideRecipeUseCase(recipeRepository: RecipeRepository): RecipeUseCase = RecipeUseCaseImpl(recipeRepository)

}