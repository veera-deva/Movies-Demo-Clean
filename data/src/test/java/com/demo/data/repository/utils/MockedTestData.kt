package com.demo.data.repository.utils

import com.demo.data.mapper.movies.toDomain
import com.demo.data.model.movies.MovieResponse

internal object MockedTestData {
    fun mockedMovieResponse() =
        listOf(
            MovieResponse
                (
                id = 1,
                title = "Mock title 1",
                description = "Mock Description 1",
                image = "Mock url 1",
                category = "Mock category 1"
            ).toDomain(), MovieResponse(
                id = 2,
                title = "Mock title 2",
                description = "Mock Description 2",
                image = "Mock url 2",
                category = "Mock category 2"
            ).toDomain()
        ).toList()

}