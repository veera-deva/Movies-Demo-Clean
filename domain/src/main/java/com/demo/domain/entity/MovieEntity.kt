package com.demo.domain.entity

/**
 * Movies API response entity data class for view
 * */
data class MovieEntity(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val image: String = "",
    val category: String = ""
)