package com.demo.data.di

import com.demo.data.BuildConfig
import com.demo.data.api.MovieApi
import com.demo.data.repository.movies.MovieDataSource
import com.demo.data.repository.movies.MovieRemoteDataSource
import com.demo.data.repository.movies.MovieRepositoryImpl
import com.demo.domain.repository.MovieRepository
import com.demo.domain.usecase.GetMoviesUseCaseImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val RETROFIT_TIMEOUT = 10L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://movies-mock-server.vercel.app/"

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesMoshiConvertor(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun providesOKHttpClient(): OkHttpClient =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(httpLoggingInterceptor)
            }
            readTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    /* OkHttpClient.Builder()
         .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
         .build()*/


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieUseCaseImpl(movieRepository: MovieRepository) =
        GetMoviesUseCaseImpl(movieRepository)

    @Provides
    @Singleton
    fun providesMovieRepository(movieDataSource: MovieDataSource) =
        MovieRepositoryImpl(movieDataSource)


    @Provides
    @Singleton
    fun providesMovieDataSource(movieApi: MovieApi) = MovieRemoteDataSource(movieApi)
}