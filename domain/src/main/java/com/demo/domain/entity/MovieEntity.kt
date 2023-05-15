package com.demo.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Movies API response entity data class for view
 * */
@Parcelize
data class MovieEntity(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val image: String = "",
    val category: String = ""
) : Parcelable