package com.example.bonialchallenge.di

import com.example.bonialchallenge.feature.repository.ProductListRepository
import com.example.bonialchallenge.feature.repository.ProductListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductListRepository(repository: ProductListRepositoryImpl): ProductListRepository = repository

}