package com.demo.domain.di

import com.demo.domain.repository.MovieRepository
import com.demo.domain.usecase.GetMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    //    @Binds
//    abstract fun bindGetMovieUseCase(getMoviesUseCaseImpl: GetMoviesUseCaseImpl): MovieRepository
//    @Provides
//    @Singleton
//    fun provideGetMoviesUseCase(movieRepository: MovieRepository) =
//        GetMoviesUseCaseImpl(movieRepository)

}