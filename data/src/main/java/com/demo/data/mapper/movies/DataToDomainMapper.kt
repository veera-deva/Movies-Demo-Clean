package com.demo.data.mapper.movies

import com.demo.data.model.movies.MovieResponse
import com.demo.domain.entity.MovieEntity

/**
 * Data to Domain layer Mapper class for movies api response
 * */
fun MovieResponse.toDomain() = MovieEntity(
    id = id, image = image, title = title, description = description, category = category
)