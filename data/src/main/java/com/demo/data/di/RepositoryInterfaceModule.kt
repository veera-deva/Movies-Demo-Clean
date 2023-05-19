package com.demo.data.di

import com.demo.data.repository.movies.MovieDataSource
import com.demo.data.repository.movies.MovieRemoteDataSource
import com.demo.data.repository.movies.MovieRepositoryImpl
import com.demo.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryInterfaceModule {
    @Binds
    internal abstract fun bindRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    internal abstract fun bindDataSource(movieRemoteDataSource: MovieRemoteDataSource): MovieDataSource

}