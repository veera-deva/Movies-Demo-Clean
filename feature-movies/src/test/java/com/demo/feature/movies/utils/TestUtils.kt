package com.demo.feature.movies.utils

import com.demo.domain.entity.MovieEntity

internal object TestUtils {

    const val NETWORK_ERROR_MSG = "Network Error"

    fun mockedMovieList() = listOf(
        MovieEntity(
            1,
            "Mock title",
            "Mock Description",
            "mockUrl", "mockCategory"
        )
    )
}