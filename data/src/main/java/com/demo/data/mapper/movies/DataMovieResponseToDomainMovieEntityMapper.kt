package com.demo.data.mapper.movies

import com.demo.data.mapper.ListMapper
import com.demo.data.model.movies.MovieResponse
import com.demo.domain.entity.MovieEntity
import javax.inject.Inject


class DataMovieResponseToDomainMovieEntityMapper @Inject constructor() :
    ListMapper<MovieResponse, MovieEntity> {
    override fun map(input: List<MovieResponse>): List<MovieEntity> =
        input.map { it.toDomain() }
}
