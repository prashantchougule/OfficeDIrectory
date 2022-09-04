package com.example.officedirectory.di

import com.example.officedirectory.data.repository.DirectoryRepository
import com.example.officedirectory.data.repository.DirectoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideDirectoryRepository(repositoryImpl: DirectoryRepositoryImpl): DirectoryRepository
}