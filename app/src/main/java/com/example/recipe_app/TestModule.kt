package com.example.recipe_app

import com.example.recipe_app.repositories.TestRepository
import com.example.recipe_app.repositories.TestRepositoryImpl
import com.example.recipe_app.use_cases.TestUseCase
import com.example.recipe_app.use_cases.TestUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    fun provideTestRepository(): TestRepository = TestRepositoryImpl()

    @Provides
    fun provideTestUseCase(
        testRepository: TestRepository
    ): TestUseCase = TestUseCaseImpl(
        testRepository = testRepository
    )
}